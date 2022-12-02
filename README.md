# Spring Boot with Auth0 integration

## Quickstart:

### Configure Auth0
+ Sign up an Auth0 account
+ Go to Applications, create "Regular Web Application"
+ Configure "Allowed Callback URLs" to `http://localhost:8080/callback`
+ Configure "Allowed Logout URLs" to `http://localhost:8080/logoutCallback`
+ Copy `domain`, `clientId`, and `clientSecret` to `config/application.yml`

### Configure Twilio
+ Sign up Twilio
+ Get a trial Twilio phone number in Dashboard
+ Go back to Auth0's "Multi-factor Authentication", enable "Phone Message", "Twilio", "SMS"
+ Copy `Account SID`, `Auth Token`, and `My Twilio phone number` from Twilio to Auth0

### Run Application
+ Run the project's `Application`
+ Login using `http://localhost:8080/login`
+ Logout using `http://localhost:8080/logout`

The response of `/login` process looks like this

```json
{
    "accessToken": "<accessToken>",
    "idToken": "<idToken>",
    "refreshToken": null,
    "type": "Bearer",
    "expiresIn": 86400
}
```

The id_token can be [JWT-decoded](https://jwt.io/) into 

```json
{
    "email": "duchieu.nguyen@zuhlke.com",
    "email_verified": false,
    "iss": "https://xyz.auth0.com/",
    "sub": "auth0|62da51d635c99dc8b8c64f17",
    "aud": "Ddd0KdKJlDKXcXpSGnAtgp1G8CrwbFQA",
    "iat": 1658732605,
    "exp": 1658768605,
    "acr": "http://schemas.openid.net/pape/policies/2007/06/multi-factor",
    "amr":
    [
        "mfa"
    ]
}
```

`sub` is the Auth0's userId that can be used for following API calls.
