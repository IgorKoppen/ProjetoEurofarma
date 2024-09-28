import '/auth/custom_auth/auth_util.dart';
import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/custom_functions.dart' as functions;
import 'package:flutter/material.dart';

Future<RefreshTokenStruct?> refreshToken(BuildContext context) async {
  ApiCallResponse? refreshToken;

  if (functions.isTokenExpired(currentAuthTokenExpiration!) == true) {
    refreshToken = await RefreshTokenCall.call(
      userName: currentUserData?.employeeRegistration,
      token: currentAuthRefreshToken,
    );

    if ((refreshToken.succeeded ?? true)) {
      context.goNamed('LoginPage');

      return null;
    } else {
      context.goNamed('LoginPage');

      return null;
    }
  } else {
    return null;
  }
}
