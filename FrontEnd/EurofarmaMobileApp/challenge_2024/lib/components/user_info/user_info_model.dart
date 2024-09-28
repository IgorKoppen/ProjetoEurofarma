import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'user_info_widget.dart' show UserInfoWidget;
import 'package:flutter/material.dart';

class UserInfoModel extends FlutterFlowModel<UserInfoWidget> {
  ///  Local state fields for this component.

  UserProfileStruct? userInfo;
  void updateUserInfoStruct(Function(UserProfileStruct) updateFn) {
    updateFn(userInfo ??= UserProfileStruct());
  }

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {}
}
