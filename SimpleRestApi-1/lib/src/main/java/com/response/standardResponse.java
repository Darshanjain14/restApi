package com.response;

import com.google.gson.JsonElement;

public class standardResponse {
	
	private String statusResponse;
	private String message;
	private JsonElement data;
	
	public standardResponse()
	{
		
	}
	public standardResponse(String statusResponse)
	{
		this.statusResponse=statusResponse;
	}
	public standardResponse(String statusResponse,String message)
	{
		this.statusResponse=statusResponse;
		this.message = message;
	}
	public standardResponse(String statusResponse,String message,JsonElement data)
	{
		this.statusResponse=statusResponse;
		this.message  = message;
		this.data =data;
	}
	
	public String getStatusResponse() {
		return statusResponse;
	}
	
	public void setStatusResponse(String statusResponse) {
		this.statusResponse = statusResponse;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JsonElement getData() {
		return data;
	}
	public void setData(JsonElement data) {
		this.data = data;
	}

}
