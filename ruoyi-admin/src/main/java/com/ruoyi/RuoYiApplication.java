package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Silergy 工作流审批系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "                                                                                                                                                      \n" +
                "                                                                                                                                                      \n" +
                "   SSSSSSSSSSSSSSS        iiii       lllllll                                                                                                          \n" +
                " SS:::::::::::::::S      i::::i      l:::::l                                                                                                          \n" +
                "S:::::SSSSSS::::::S       iiii       l:::::l                                                                                                          \n" +
                "S:::::S     SSSSSSS                  l:::::l                                                                                                          \n" +
                "S:::::S                 iiiiiii       l::::l          eeeeeeeeeeee         rrrrr   rrrrrrrrr           ggggggggg   ggggg     yyyyyyy           yyyyyyy\n" +
                "S:::::S                 i:::::i       l::::l        ee::::::::::::ee       r::::rrr:::::::::r         g:::::::::ggg::::g      y:::::y         y:::::y \n" +
                " S::::SSSS               i::::i       l::::l       e::::::eeeee:::::ee     r:::::::::::::::::r       g:::::::::::::::::g       y:::::y       y:::::y  \n" +
                "  SS::::::SSSSS          i::::i       l::::l      e::::::e     e:::::e     rr::::::rrrrr::::::r     g::::::ggggg::::::gg        y:::::y     y:::::y   \n" +
                "    SSS::::::::SS        i::::i       l::::l      e:::::::eeeee::::::e      r:::::r     r:::::r     g:::::g     g:::::g          y:::::y   y:::::y    \n" +
                "       SSSSSS::::S       i::::i       l::::l      e:::::::::::::::::e       r:::::r     rrrrrrr     g:::::g     g:::::g           y:::::y y:::::y     \n" +
                "            S:::::S      i::::i       l::::l      e::::::eeeeeeeeeee        r:::::r                 g:::::g     g:::::g            y:::::y:::::y      \n" +
                "            S:::::S      i::::i       l::::l      e:::::::e                 r:::::r                 g::::::g    g:::::g             y:::::::::y       \n" +
                "SSSSSSS     S:::::S     i::::::i     l::::::l     e::::::::e                r:::::r                 g:::::::ggggg:::::g              y:::::::y        \n" +
                "S::::::SSSSSS:::::S     i::::::i     l::::::l      e::::::::eeeeeeee        r:::::r                  g::::::::::::::::g               y:::::y         \n" +
                "S:::::::::::::::SS      i::::::i     l::::::l       ee:::::::::::::e        r:::::r                   gg::::::::::::::g              y:::::y          \n" +
                " SSSSSSSSSSSSSSS        iiiiiiii     llllllll         eeeeeeeeeeeeee        rrrrrrr                     gggggggg::::::g             y:::::y           \n" +
                "                                                                                                                g:::::g            y:::::y            \n" +
                "                                                                                                    gggggg      g:::::g           y:::::y             \n" +
                "                                                                                                    g:::::gg   gg:::::g          y:::::y              \n" +
                "                                                                                                     g::::::ggg:::::::g         y:::::y               \n" +
                "                                                                                                      gg:::::::::::::g         yyyyyyy                \n" +
                "                                                                                                        ggg::::::ggg                                  \n" +
                "                                                                                                           gggggg                                     ");
    }
}