package com.techurate.api.config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	"system:properties",
	//"system:env",
	"classpath:config.properties",
	//"file:${user.dir}/src/main/resources/config.properties"	
})
public interface ApiConfig extends Config {

     @DefaultValue("uat")
	@Key("environment")
	String environment();

	@Key("${environment}.server")
	String server();

	@Key("${environment}.resource")
	String resource();

	@Key("${environment}.username")
	String username();
	
	@Key("${environment}.mobileNumber")
	String mobileNo();
	
	@Key("${environment}.password")
	String password();
	
	@Key("${environment}.otp")
	String otp();
	
	@Key("${environment}.profileName")
	String profileName();
	
	@Key("${environment}.channelTypeWeb")
	String channelTypeWeb();
	
	@Key("${environment}.channelTypeMobile")
	String channelTypeMobile();
	
	@Key("faker.locale")
	String faker();

	@Key("browser")
	String browser();

	@Key("headless")
	String headless();

	@Key("timeout")
	long timeout();
	
	@Key("${environment}.reportName")
	String reportName();
	
	@Key("${environment}.reportTitle")
	String reportTitle();
}
