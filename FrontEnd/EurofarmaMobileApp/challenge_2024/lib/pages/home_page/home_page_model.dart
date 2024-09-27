import '/backend/schema/structs/index.dart';
import '/components/card_training/card_training_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'dart:async';
import 'home_page_widget.dart' show HomePageWidget;
import 'package:flutter/material.dart';

class HomePageModel extends FlutterFlowModel<HomePageWidget> {
  ///  State fields for stateful widgets in this page.

  // Stores action output result for [Action Block - RefreshToken] action in HomePage widget.
  RefreshTokenStruct? userInfo;
  bool apiRequestCompleted = false;
  String? apiRequestLastUniqueKey;
  // Models for CardTraining dynamic component.
  late FlutterFlowDynamicModels<CardTrainingModel> cardTrainingModels;

  @override
  void initState(BuildContext context) {
    cardTrainingModels = FlutterFlowDynamicModels(() => CardTrainingModel());
  }

  @override
  void dispose() {
    cardTrainingModels.dispose();
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
