package com.linkdev.localizationHelper.uitils

import android.Manifest

object Constants {

    object RequestCodes {
        const val REQUEST_APP_SETTINGS = 1000
        const val GALLERY_REQUEST_CODE = 1

    }


    object SecurityConstants {
        val KEY_SIZE = 2048
        val ALGORITHM = "RSA"
        internal val MODE = "ECB"
        internal val PADDING = "PKCS1Padding"
        val TRANSFORMATION = "$ALGORITHM/$MODE/$PADDING"

        val KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore"
    }

    object MimeType {
        const val MimeType_IMAGE = "image/*"
    }

    object Files {
        const val FOLDER_NAME = "GAFI"
        const val MAX_FILE_SIZE_MB = 5
        const val FILE_PREFIX = "gafi_"
        const val CAPTURED_IMAGE_EXTENTION = ".jpg"

        const val FAILED_DOWNLOAD_ID = -1L


        const val FILE_TYPE_PDF = ".pdf"
        const val FILE_TYPE_JPG = ".jpg"
        const val FILE_TYPE_PNG = ".png"
        const val FILE_TYPE_JPEG = ".jpeg"
        const val FILE_TYPE_PPTX = ".pptx"
        const val FILE_TYPE_PPT = ".ppt"
        const val FILE_TYPE_XLS = ".xls"
        const val FILE_TYPE_XLSX = ".xlsx"
        const val FILE_TYPE_DOC = ".doc"
        const val FILE_TYPE_DOCX = ".docx"
        const val CAPTURED_IMAGE_NAME = "yyyyMMdd_HHmmss"
    }

    object DeepLinks {
        const val SERVICE_CATEGORY_DEEP_LINK = "serviceCategory://serviceListing/"
        const val MY_BOOKINGS_DEEP_LINK = "login://myBookings/"
    }

    object Network {
        const val CONNECT_TIMEOUT: Long = 5
        const val READ_TIMEOUT: Long = 5
        const val WRITE_TIMEOUT: Long = 5
        const val HeaderLanguage = "Language"
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
        const val AUTHORIZATION = "UserToken"
        const val DEVICE_TOKEN = "deviceToken"

    }

    object ErrorCodes {
        const val NOT_AUTHORIZED = 2
        const val INVALID_USER_TOKEN = 5

        object Login {
            const val WRONG_EMAIL_OR_PASSWORD = 4
            const val ACCOUNT_NOT_ACTIVATED = 3
        }

        object EditProfile {
            const val DUPLICATED_EMAIL = 11
            const val INVALID_EMAIL_ADDRESS = 8

        }

        object ChangePassword {
            const val INVALID_OLD_PASSWORD = 9
        }

        object Registration {
            const val DUPLICATED_EMAIL = 11
            const val INVALID_EMAIL_ADDRESS = 8
            const val EXISTS_USERNAME = 12
            const val INCORRECT_PASSWORD = 7
        }

        object AuthorizedCompanies {
            const val NO_ACCESS_TO_ANY_COMPANY = 16
            const val NO_DATA_FOR_QUERY = 17
        }

        object ForgotPassword {
            const val INVALID_USERNAME_EMAIL = 10
        }

        object WorkSpace {
            const val IS_ESIGNATURE = 100
        }
    }

    object Extras {
        const val NO_ACTION = -1
        const val CHANGE_LANGUAGE_REDIRECTION = "CHANGE_LANGUAGE_REDIRECTION"
        const val NEW_ITEM_KEY = "NEW_ITEM_KEY"
        const val AVAILABLE_SLOTS_LIST = "AVAILABLE_SLOTS_LIST"
    }



    object Apis {
        const val REGISTER_UNREGISTER_TOKEN = "MobileAuthentication/RegisterUnRegisterDeviceToken"
        const val GET_AUTHORIZED_COMPANIES = "MobileAuthentication/GetAuthroizedCompanies"
        const val CHANGE_PAASWORD = "MobileAuthentication/ChangePassword"
        const val EDIT_PROFILE = "MobileAuthentication/EditProfile"
        const val FORGOT_PASSWORD = "MobileAuthentication/ForgetPassword"
        const val REGISTRATION = "MobileAuthentication/RegisterUser"
        const val SERVICES_CATEGORIES = "GAFIServices/GetServiceCatalogCategories"
        const val NEWS_LISTING = "News/GetAllNews"
        const val LOGIN = "MobileAuthentication/Login"
        const val RESEND_ACTIVATION_MAIL = "MobileAuthentication/ResendActivationMail"
        const val SERVICES_LIST = "GAFIServices/GetServicesListByCategoryID"
        const val SERVICES_DETAILS = "GAFIServices/GetServicesDetails"
        const val GET_ALL_NATIONALITIES = "MobileLookups/GetAllNationalities"
        const val GET_ALL_COUNTRIES = "MobileLookups/GetAllCountries"
        const val ABOUT = "AboutGAFI/GetAboutGAFI"
        const val COMPANY_SEARCH = "MobileLookups/SearchCompanyForAutoCompelete"
        const val COMMERCIAL_REGISTER_OFFICES = "MobileLookups/GetAllCommercialRegisterOffices"
        const val ROLES = "Roles/GetAllRoles"
        const val GET_PROFILE = "MobileAuthentication/GetProfile"
        const val MY_TASKS = "Tasks/GetMyTasks"
        const val LOGOUT = "MobileAuthentication/Logout"
        const val SERVICES_TYPES = "MobileLookups/GetAllEservicesTypes"
        const val SERVICES_NAMES = "MobileLookups/GetAllEservices"
        const val CONTACT_US = "ContactUs/GetContactUsInfo"
        const val SUBMIT_AUTH = "Requests/SubmitAuthorizedRequest"
        const val GET_INQUIRY_CATEGORIES = "InquiryCategory/GetInquiriesCategories"
        const val SUBMIT_INQUIRY = "InquiryCategory/SubmitInquiry"
        const val MY_REQUESTS = "Tasks/GetMyRequests"
        const val MY_SUBORDINATES = "Tasks/GetMySubordinatesRequests"
        const val APPOINTMENT_CALENDAR_SERVICES = "MobAppointment/GetAllServices"
        const val APPOINTMENT_CALENDAR_CENTERS = "MobAppointment/GetAllCenters"
        const val APPOINTMENT_CALENDAR_CONFIGURATIONS = "MobAppointment/GetServerConfigrations"
        const val APPOINTMENT_CALENDAR_AVAILABLE_SLOTS = "MobAppointment/RetrieveAppointments"
        const val SHORTCUTS = "MobContentPages/GetAllContentPages"
        const val MY_BOOKINGS = "MobAppointment/GetMyBookings"
        const val CANCEL_BOOKING = "MobAppointment/CancelSlot"
        const val SET_DEVICE_LANG = "MobileAuthentication/SetDeviceLanguage"
        const val SUBMIT_SLOT = "MobAppointment/SubmitSlot"
    }

    object ApisTags {
        const val REGISTER_UNREGISTER_TOKEN = "REGISTER_UNREGISTER_TOKEN"
        const val SUBMIT_INQUIRY = "SUBMIT_INQUIRY"
        const val SUBMIT_SLOT = "EDIT_PROFILE"
        const val EDIT_PROFILE = "EDIT_PROFILE"
        const val GET_PROFILE = "GET_PROFILE"
        const val GET_APPOINTMENT_CONFIGURATIONS = "GET_APPOINTMENT_CONFIGURATIONS"
        const val SERVICES_CATEGORIES = "SERVICES_CATEGORIES"
        const val NEWS_LISTING = "NEWS_LISTING"
        const val GENDER = "GENDER"
        const val GET_ALL_NATIONALITIES = "GET_ALL_NATIONALITIES"
        const val GET_APPOINTMENT_SERVICES = "GET_APPOINTMENT_SERVICES"
        const val GET_APPOINTMENT_CENTERS = "GET_APPOINTMENT_CENTERS"
        const val GET_AUTHORIZED_COMPANIES = "GET_AUTHORIZED_COMPANIES"
        const val GET_AVAILABLE_SLOTS = "GET_AVAILABLE_SLOTS"
        const val GET_INQUIRY_CATEGORIES = "GET_INQUIRY_CATEGORIES"
        const val LOGIN = "LOGIN"
        const val FORGOT_PASSWORD = "FORGOT_PASSWORD"
        const val CHANGE_DEVICE_LANG = "CHANGE_DEVICE_LANG"
        const val REGISTER_USER = "REGISTER_USER"
        const val CHANGE_PASSWORD = "CHANGE_PASSWORD"
        const val RESEND_ACTIVATION_MAIL = "RESEND_ACTIVATION_MAIL"
        const val SERVICES_LIST = "SERVICES_LIST"
        const val SERVICES_DETAILS = "SERVICES_DETAILS"
        const val ABOUT = "ABOUT"
        const val MY_TASKS = "MY_TASKS"
        const val COMPANY_SEARCH = "COMPANY_SEARCH"
        const val LOGOUT = "LOGOUT"
        const val COMMERCIAL_REGISTER_OFFICES = "COMMERCIAL_REGISTER_OFFICES"
        const val ROLES = "ROLES"
        const val SERVICES_TYPES = "SERVICES_TYPES"
        const val SERVICES_NAMES = "SERVICES_NAME"
        const val CONTACT_US = "CONTACT_US"
        const val SUBMIT_AUTH = "SUBMIT_AUTH"
        const val MY_REQUESTS = "MY_REQUESTS"
        const val MY_SUBORDINATES = "MY_SUBORDINATES"
        const val SHORTCUTS = "SHORTCUTS"
        const val MY_BOOKINGS = "MY_BOOKINGS"
        const val CANCEL_BOOKING = "CANCEL_BOOKING"
    }

    object ApiParams {
        const val AND = "&"
        const val QUESTION_MARK = "?"
        const val COMMERCIAL_FILE_PARAM = "commercialRegisterFile"
        const val ID_COPY_FILE_PARAM = "idCopy"
        const val AUTH_LETTER_FILE_PARAM = "authorizationLetter"
        const val COMMERCIAL_REGISTER_DATE = "CommercialRegisterDate"
        const val COMMERCIAL_REGISTER_NUMBER = "CommercialRegisterNumber"
        const val COMMERCIAL_REGISTER_OFFICE = "CommercialRegisterOfficeID"
        const val TAX_REGISTER_NUMBER = "TaxRegisterNumber"
        const val FOREIGN_COMPANY = "ForeignCompany"
        const val BIRTH_DATE = "BirthDate"
        const val ROLE_ID = "RoleID"
        const val PROJECT_CODE = "ProjectCode"
        const val COMPANY_NAME = "CompanyName"
        const val ROLE_OTHER = "RoleOthers"
        const val GET_NOTIFICATION = "GetNotification"
        const val QUERY_USER_TOKEN = "userToken="
        const val QUERY_IS_MOBILE = "isMobile=true"
        const val QUERY_BOOKING_ID = "bookingID"
    }

    object APIsRequestsTags {
    }

    object Animation {
        const val SMALL_DURATION = 1000L
        const val PROGRESS_START_POINT = 0f
        const val PROGRESS_END_POINT = 360f
        const val PROGRESS_REPEAT_COUNT = android.view.animation.Animation.INFINITE


    }

    object AppPreference {
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val APP_LOCALE_KEY = "APP_LOCALE_KEY"
        const val USER_CREDENTIALS = "USER_CREDENTIALS"
        const val USER_RESPONSE = "USER_RESPONSE"
        const val DEVICE_TOKEN = "DEVICE_TOKEN"
        const val PROFILE_DATE = "PROFILE_DATE"
        const val SET_USER_LOGGED_IN = "SET_USER_LOGGED_IN"
        const val SECRET_KEY = "SECRET_KEY"

    }

    object Languages {
        const val SERVER_ENGLISH_LANGUAGE = "en-US"
        const val ENGLISH_LANGUAGE = "en"
        const val ARABIC_LANGUAGE = "ar"
        const val DEFAULT_LANGUAGE = ENGLISH_LANGUAGE
    }

    object ContentDirection {
        const val RTL = "rtl"
        const val LTR = "ltr"
    }

    object ApiStatus {
        const val STATUS_SUCCESS = 0
        const val STATUS_FAIL = 1
        const val NO_STATUS = -1
        const val NO_HTTP_CODE = -1
    }

    object DefaultRequestConstants {
        const val MAX_CELLS_IN_AVAILABLE_SLOTS_VIEW = 12
        const val MALE_ID = "0"
        const val FEMALE_ID = "1"
        const val COUNRTY_EGYPT_ID = "818"
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10
        const val DEFAULT_RELATION_ITEM_COUNT = 5
        const val DEFAULT_LOOKUP_PAGE_SIZE = 30
        const val SORT_ALPHABETICAL = SortBy.TITLE
        const val SORT_ASCENDING = SortDirection.ASCENDING
        const val COMPANY_NAME = "CompanyName"
        const val YES_ID = "1"
        const val NO_ID = "0"
        const val OTHER_ROLE_ID = "26"
        const val NATIONAL_ID = "1"
        const val PASSPORT = "2"
        const val LEGAL_AGE = 21
    }

    object SortDirection {
        const val ASCENDING = 1
        const val DESCENDING = 2
    }

    object SortBy {
        const val TITLE = 1
        const val CREATION_DATE = 2
        const val PRICE = 3
    }

    object NamedAnnotations {
        const val IS_USER_LOGGED_IN: String = "IS_USER_LOGGED_IN"
        const val LANGUAGE: String = "LANGUAGE"
        const val CATEGORY: String = "CATEGORY"
        const val NEWS_DETAILS: String = "NEWS_DETAILS"
        const val SHORTCUTS_CONTENT_SUB_PAGES_LIST: String = "SHORT_CUT_CONTENT_SUB_PAGES_LIST"
        const val SHORTCUTS_CONTENT_SUB_PAGES_TITLE: String = "SHORTCUTS_CONTENT_SUB_PAGES_TITLE"
        const val SERVICES: String = "SERVICES"
        const val CONTENT_ARGS: String = "CONTENT_ARGS"
        const val WEB_VIEW_URL: String = "WEB_VIEW_URL"
        const val WEB_VIEW_TITLE: String = "WEB_VIEW_TITLE"
    }

    object Common {
        const val EMPTY_TEXT = ""
    }

    object PermissionTags {
        const val WRITE_EXTERNAL_STORAGE_TAG = "WRITE_EXTERNAL_STORAGE_TAG"
        const val READ_EXTERNAL_STORAGE_TAG = Manifest.permission.READ_EXTERNAL_STORAGE
        const val CAMERA_PERMISSIONS_TAG = "CAMERA_PERMISSIONS_TAG"
        const val FILE_PERMISSIONS_TAG = "FILE_PERMISSIONS_TAG"
        const val AUTHORIZATION_LETTER_PERMISSIONS_TAG = "AUTHORIZATION_LETTER_PERMISSIONS_TAG"
        const val ID_COPY_PERMISSIONS_TAG = "ID_COPY_PERMISSIONS_TAG"
        const val AUTH_LETTER_CAMERA_PERMISSIONS_TAG = "AUTH_LETTER_CAMERA_PERMISSIONS_TAG"
        const val ID_COPY_CAMERA_PERMISSIONS_TAG = "ID_COPY_CAMERA_PERMISSIONS_TAG"
        const val AUTH_LETTER_FILE_PERMISSIONS_TAG = "AUTH_LETTER_FILE_PERMISSIONS_TAG"
        const val ID_COPY_FILE_PERMISSIONS_TAG = "ID_COPY_FILE_PERMISSIONS_TAG"

    }

    object RequestCode {
        const val COMMERCIAL_REGISTER_REQUEST_CODE = 5000
        const val AUTHORIZATION_LETTER_REQUEST_CODE = 5001
        const val ID_COPY_REQUEST_CODE = 5002
    }

    object FileConstants {
        const val FILE_MAX_SIZE = 5
    }

    object ValidationLength {
        const val SEARCH_MIN_LENGTH = 3
    }

    object ApisTimeOut {
        const val SEARCH_TIMEOUT = 500L
    }
}