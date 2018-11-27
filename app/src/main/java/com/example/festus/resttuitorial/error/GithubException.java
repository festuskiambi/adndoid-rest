package com.example.festus.resttuitorial.error;

import java.io.IOException;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
class GithubException extends IOException {

    private int responseCode;
    private String responseMesaage;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMesaage() {
        return responseMesaage;
    }

    public GithubException(int code, String message) {
      this.responseCode = code;
      this.responseMesaage = message;

    }
}
