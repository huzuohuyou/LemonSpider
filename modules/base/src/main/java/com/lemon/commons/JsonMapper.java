package com.lemon.commons;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lemon.commons.log.Log;

public final class JsonMapper {
	private final static Log log = Log.getLogger(JsonMapper.class);

	private static JsonMapper instance = new JsonMapper();

	public static JsonMapper sharedInstance() {
		if(instance == null) {
			instance = new JsonMapper();
		}
		return instance;
	}


	private ObjectMapper mapper = new ObjectMapper();
	private  Gson gson;
	private JsonMapper() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	}

	public JsonRest json2Rest(String jsonStr) {
		try {
			return mapper.readValue(jsonStr, JsonRest.class);
		} catch (Exception e) {
			log.error("[error] json -> object ", e);
			return null;
		}
	}
	public <T> T json2Object(String jsonStr, Class<T> valueType) {
		try {
			return mapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			log.error("[error] json -> object {}", e.getMessage());
			return null;
		}
	}

	public <T> T json2List(String jsonStr,Class<?> collectionClass, Class<?>... elementClasses) {
		try {
			JavaType javaType =getCollectionType(collectionClass, elementClasses);
			return mapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			log.error("[error] json -> list ", e);
			return null;
		}
	}

	public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public Map<?, ?> object2jsonobject(Object value){
		Map<String,String> map=new HashMap<String,String>();

		return gson.fromJson(gson.toJsonTree(value),map.getClass());
	}

	public String object2json(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.error("[error] object -> json ", e);
			return null;
		}
	}

}
