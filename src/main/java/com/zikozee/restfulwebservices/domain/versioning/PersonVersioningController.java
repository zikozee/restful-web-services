package com.zikozee.restfulwebservices.domain.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zikoz
 * @created : 29 Apr, 2021
 */
@RestController
public class PersonVersioningController {

    //todo --->>> URI METHOD VERSIONING  e.g Twitter
    @GetMapping(path = "v1/person")
    public PersonV1 personV1(){
        return  new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //todo --->>> REQUEST PARAMETER VERSIONING   e.g Amazon
    @GetMapping(path = "person/param", params = "version=1")
    public PersonV1 personParamV1(){
        return  new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "person/param", params = "version=2")
    public PersonV2 personParamV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //todo --->>> HEADER VERSIONING   set header,    X-API-VERSION as key and value as 1    e.g Microsoft
    @GetMapping(path = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1(){
        return  new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //todo --->>> MEDIA-TYPE VERSIONING   set header,    Accept as key and value as application/com.zikozee.app-v1+json   e.g Github
    @GetMapping(path = "person/produces", produces = "application/com.zikozee.app-v1+json")
    public PersonV1 personProducesV1(){
        return  new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "person/produces", produces = "application/com.zikozee.app-v2+json")
    public PersonV2 personProducesV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }


    //FACTORS TO NOTE:          CULPRITS
    // -URI Pollution         === URI and Request Param
    // -Misuse of Http Headers == header and media type
    // -Caching                 == header and media type
    // -Can we execute the request on the browser ===  header and media type
    // -API documentation       ==  header and media type
    //              -No Perfect Solution
}
