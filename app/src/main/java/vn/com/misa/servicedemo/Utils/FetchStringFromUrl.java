package vn.com.misa.servicedemo.Utils;

import android.content.Context;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * Create by Nguyễn Bá Linh on 17/03/2019
 * Mô tả: Lấy string json từ url
 */
public class FetchStringFromUrl {

    private DataCallback<String> mCallback;
    private Context mContext;

    public FetchStringFromUrl(Context context, DataCallback<String> callback) {
        mContext = context;
        mCallback = callback;
    }

    public void getJSONStringFromURL(String urlString) {
        HttpsURLConnection httpsURLConnection = null;
        String jsonString = null;
        try {
            ProviderInstaller.installIfNeeded(mContext);
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
            SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslContext.getSocketFactory());

            HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);
            URL url = new URL(urlString);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod(Constant.REQUEST_METHOD_GET);
            httpsURLConnection.setReadTimeout(Constant.READ_TIME_OUT);
            httpsURLConnection.setConnectTimeout(Constant.CONNECT_TIME_OUT);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(Constant.BREAK_LINE);
            }
            br.close();
            httpsURLConnection.disconnect();
            jsonString = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        if (jsonString != null) {
            mCallback.onDataSuccess(jsonString);
        } else {
            mCallback.onDataNotAvailable();
        }
    }
}
