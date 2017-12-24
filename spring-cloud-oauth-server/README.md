# Spring Cloud Oauth2 Server

Endpoints:

  - Grant Type client_credentials:
      curl -X POST \
      http://localhost:9000/services/oauth/token \
      -H 'authorization: Basic Y2xpZW50LWlkOmNsaWVudC1zZWNyZXQ=' \
      -F grant_type=client_credentials
  - Grant Type password:
      curl -X POST \
      http://localhost:9000/services/oauth/token \
      -H 'authorization: Basic Y2xpZW50LWlkOmNsaWVudC1zZWNyZXQ=' \
      -d 'grant_type=password&client_id=client-id&username=user&password=user'
