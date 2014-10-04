package com.github.sh0hei.doorkeeper4j.model.request;

import java.util.Map;

public abstract class ParamsRequest extends DoorKeeperRequest {

	public abstract Map<String, String> createParams();
}
