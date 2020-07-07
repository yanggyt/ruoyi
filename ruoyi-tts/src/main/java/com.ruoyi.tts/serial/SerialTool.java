package com.ruoyi.tts.serial;

import com.ruoyi.tts.exception.*;
import gnu.io.*;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**串口
 * @author GideonYeung
 * @date 2020/7/7 9:27
 */
@Slf4j
public class SerialTool {
    /**
     * 查找所有可用端口
     * @return 可用端口名称列表
     */
    public static ArrayList<String> findPort() {

        //获得当前所有可用串口
        @SuppressWarnings("unchecked")
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<String>();
        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }

        return portNameList;

    }

    /**
     * 打开串口
     * @param portName 端口名称
     * @param baudRate 波特率
     * @return 串口对象
     * @throws SerialPortParameterFailureException 设置串口参数失败
     * @throws NotSerialPortException 端口指向设备不是串口类型
     * @throws NoSuchPortException 没有该端口对应的串口设备
     * @throws PortInUseException 端口已被占用
     */
    public static SerialPort openPort(String portName, int baudRate) throws SerialPortParameterFailureException, NotSerialPortException, NoSuchPortException, PortInUseException {

        try {

            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {

                SerialPort serialPort = (SerialPort) commPort;

                try {
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    throw new SerialPortParameterFailureException();
                }

                log.info("Open " + portName + " successfully !");
                return serialPort;

            }
            else {
                //不是串口
                throw new NotSerialPortException();
            }
        } catch (NoSuchPortException e1) {
            throw new NotSerialPortException();
        } catch (PortInUseException e2) {
            throw new PortInUseException();
        }
    }

    /**
     * 关闭串口
     * @param serialPort 待关闭的串口对象
     */
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();

        }
    }

    /**
     * 往串口发送数据
     * @param serialPort 串口对象
     * @param order 待发送数据
     * @throws SerialPortParameterFailureException 向串口发送数据失败
     * @throws SerialPortOutputStreamCloseFailureException 关闭串口对象的输出流出错
     */
    public static void sendToPort(SerialPort serialPort, byte[] order) throws SerialPortParameterFailureException, SerialPortOutputStreamCloseFailureException {

        OutputStream out = null;

        try {

            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
            log.info("写入成功");

        } catch (IOException e) {
            throw new SerialPortParameterFailureException();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new SerialPortOutputStreamCloseFailureException();
            }
        }

    }

    /**
     * 从串口读取数据
     * @param serialPort 当前已建立连接的SerialPort对象
     * @return 读取到的数据
     * @throws ReadDataFromSerialPortFailureException 从串口读取数据时出错
     * @throws SerialPortInputStreamCloseFailureException 关闭串口对象输入流出错
     */
    public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailureException, SerialPortInputStreamCloseFailureException {

        InputStream in = null;
        byte[] bytes = null;

        try {

            in = serialPort.getInputStream();
            //获取buffer里的数据长度
            int bufflenth = in.available();

            while (bufflenth != 0) {
                //初始化byte数组为buffer中数据的长度
                bytes = new byte[bufflenth];
                in.read(bytes);
                bufflenth = in.available();
            }
        } catch (IOException e) {
            throw new ReadDataFromSerialPortFailureException();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch(IOException e) {
                throw new SerialPortInputStreamCloseFailureException();
            }

        }
        log.info("读取成功");
        return bytes;

    }

    /**
     * 添加监听器
     * @param port 串口对象
     * @param listener 串口监听器
     * @throws TooManyListenersException 监听类对象过多
     */
    public static void addListener(SerialPort port, DataAvailableListener listener) throws TooManyListenersException {

        try {

            //给串口添加监听器
            port.addEventListener(new SerialPortListener(listener));
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);

        } catch (TooManyListenersException e) {
            throw new TooManyListenersException();
        }
    }
    /**
     * 串口监听
     */
    public static class SerialPortListener implements SerialPortEventListener {

        private DataAvailableListener mDataAvailableListener;

        public SerialPortListener(DataAvailableListener mDataAvailableListener) {
            this.mDataAvailableListener = mDataAvailableListener;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            switch (serialPortEvent.getEventType()) {
                // 1.串口存在有效数据
                case SerialPortEvent.DATA_AVAILABLE:
                    if (mDataAvailableListener != null) {
                        mDataAvailableListener.dataAvailable();
                    }
                    break;
                // 2.输出缓冲区已清空
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    break;
    // 3.清除待发送数据
                case SerialPortEvent.CTS:
                    break;
                // 4.待发送数据准备好了
                case SerialPortEvent.DSR:
                    break;
                // 5.振铃指示
                case SerialPortEvent.RI:
                    break;
                // 6.载波检测
                case SerialPortEvent.CD:
                    break;
                // 7.溢位（溢出）错误
                case SerialPortEvent.OE:
                    break;
                // 8.奇偶校验错误
                case SerialPortEvent.PE:
                    break;
                // 9.帧错误
                case SerialPortEvent.FE:
                    break;
                // 10.通讯中断
                case SerialPortEvent.BI:
                    log.error("与串口设备通讯中断");
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 串口存在有效数据监听
     */
    public interface DataAvailableListener {
        /**
         * 串口存在有效数据
         */
        void dataAvailable();
    }

}
