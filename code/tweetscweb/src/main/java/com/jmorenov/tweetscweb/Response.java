package com.jmorenov.tweetscweb;

/**
 * Response class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Response {
    private String status;
    private Object data;

    /**
     * Default constructor of the class.
     */
    public Response() {}

    /**
     * Constructor of the class.
     * @param status String with the status of the response.
     * @param data Object with the value of the response.
     */
    public Response(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    /**
     * Method to get the status of the response.
     * @return String with the status of the response.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to define the status of the response.
     * @param status String with the status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method to get the data of the response
     * @return Object with the value of the response.
     */
    public Object getData() {
        return data;
    }

    /**
     * Method to define the data of the response
     * @param data Object with the value of the response.
     */
    public void setData(Object data) {
        this.data = data;
    }
}