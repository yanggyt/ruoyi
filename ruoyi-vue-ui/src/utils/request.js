const verbs = {
  POST(url, params) {
    /* istanbul ignore next */
    return fetch(url, {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params),
    });
  },
};

const request = (params = {}, url) => {
  return verbs['POST'](url, params)
    .then((res) => {
      if (res.ok) {
        return res.json();
      } else {
        return {
          success: false,
          errCode: 'NETWORK_ERROR',
          errMsg: 'Network Error',
        };
      }
    })
    .catch((e) => {
      console.log('e >>> ', e);
      return {
        success: false,
        errorCode: 'NETWORK_ERROR',
        errorMessage: 'Network Error',
      };
    });
};

export default (params = {}, url) => {
  let timeHandle;
  const timeout = 20 * 1000;
  const promiseTimeout = new Promise((resolve) => {
    timeHandle = setTimeout(() => {
      resolve({
        success: false,
        errorCode: 'NETWORK_TIMEOUT',
        errorMessage: 'Network Timeout',
      });
    }, timeout);
  });

  return Promise.race([
    request(params, url).then((result) => {
      clearTimeout(timeHandle);
      return result;
    }),
    promiseTimeout,
  ]);
};
