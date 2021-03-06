package org.ei.ziggy.service;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.ei.ziggy.domain.Response;
import org.ei.ziggy.domain.ResponseStatus;
import org.ei.ziggy.repository.AllSettings;

import static org.ei.ziggy.util.Log.logWarn;

public class HTTPAgent {
    private final DefaultHttpClient httpClient;
    private AllSettings allSettings;

    public HTTPAgent(AllSettings allSettings) {
        this.allSettings = allSettings;
        httpClient = new DefaultHttpClient();
    }

    public Response<String> fetch(String uri) {
        try {
            setCredentials(allSettings.fetchRegisteredReporter(), allSettings.fetchReporterPassword());
            HttpGet httpGet = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpGet);
            String responseContent = IOUtils.toString(response.getEntity().getContent());
            return new Response<String>(ResponseStatus.success, responseContent);
        } catch (Exception e) {
            logWarn(e.toString());
            return new Response<String>(ResponseStatus.failure, null);
        }
    }

    public Response<String> postJSONRequest(String uri, String json) {
        try {
            setCredentials(allSettings.fetchRegisteredReporter(), allSettings.fetchReporterPassword());
            HttpPost httpPost = new HttpPost(uri);
            StringEntity entity = new StringEntity(json);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(entity);
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");

            HttpResponse response = httpClient.execute(httpPost);
            String responseContent = IOUtils.toString(response.getEntity().getContent());
            return new Response<String>(ResponseStatus.success, responseContent);
        } catch (Exception e) {
            logWarn(e.toString());
            return new Response<String>(ResponseStatus.failure, null);
        }
    }

    private void setCredentials(String userName, String password) {
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY),
                new UsernamePasswordCredentials(userName, password));
    }
}
