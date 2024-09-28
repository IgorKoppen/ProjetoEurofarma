import '/backend/schema/structs/index.dart';
import '/components/user_info/user_info_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'dart:async';
import 'settings_widget.dart' show SettingsWidget;
import 'package:flutter/material.dart';

class SettingsModel extends FlutterFlowModel<SettingsWidget> {
  ///  Local state fields for this page.

  String name = 'name';

  String surname = 'surname';

  ///  State fields for stateful widgets in this page.

  // Stores action output result for [Action Block - RefreshToken] action in Settings widget.
  RefreshTokenStruct? userInfo;
  bool apiRequestCompleted = false;
  String? apiRequestLastUniqueKey;
  // Model for UserInfo component.
  late UserInfoModel userInfoModel;

  @override
  void initState(BuildContext context) {
    userInfoModel = createModel(context, () => UserInfoModel());
  }

  @override
  void dispose() {
    userInfoModel.dispose();
  }

  /// Additional helper methods.
  Future waitForApiRequestCompleted({
    double minWait = 0,
    double maxWait = double.infinity,
  }) async {
    final stopwatch = Stopwatch()..start();
    while (true) {
      await Future.delayed(const Duration(milliseconds: 50));
      final timeElapsed = stopwatch.elapsedMilliseconds;
      final requestComplete = apiRequestCompleted;
      if (timeElapsed > maxWait || (requestComplete && timeElapsed > minWait)) {
        break;
      }
    }
  }
}
