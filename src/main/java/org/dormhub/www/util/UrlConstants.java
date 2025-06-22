package org.dormhub.www.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlConstants {

    public static final String API = "/api";
    public static final String DORMHUB = "/dormhub";
    public static final String V1 = "/v1";
    public static final String BASE_API_V1 = API + DORMHUB + V1;
    public static final String ID = "id";
    public static final String INFO = "/info";
    public static final String ID_PATH = "/{" + ID + "}";
    public static final String API_SEARCH = "/search";

    public static final String AUTH = "/auth";
    public static final String SIGN_IN = "/sign-in";

    public static final String API_MESSENGER = "/messenger";
    public static final String API_CHATS = "/chats";
    public static final String API_MESSAGES = "/messages";

    public static final String API_USERS = "/users";
    public static final String API_STAFFERS = "/staffers";
    public static final String API_TENANTS = "/tenants";
    public static final String API_ROLES = "/roles";


    public static final String API_DORMS = "/dorms";
    public static final String BRIEF = "/brief";

    public static final String API_APARTMENTS = "/apartments";

    public static final String API_EQUIPMENTS = "/equipments";
    public static final String API_EQUIPMENT_LOCATIONS = "/equipment-locations";
    public static final String API_ORDERS = "/orders";
    public static final String API_DUTIES = "/duties";

}
