package com.asiainfo.meo.common.core.serialize;

public interface Serialize 
{
	<T> T serialize(byte[] bytes,Class<T> clazz);
	
	byte[] deserialize(Object o);
}
