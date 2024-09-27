import '/backend/schema/structs/index.dart';
import '/components/card_training/card_training_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'dart:async';
import 'treinamentos_widget.dart' show TreinamentosWidget;
import 'package:flutter/material.dart';

class TreinamentosModel extends FlutterFlowModel<TreinamentosWidget> {
  ///  Local state fields for this page.

  bool isShowFullList = true;

  ///  State fields for stateful widgets in this page.

  // Stores action output result for [Action Block - RefreshToken] action in Treinamentos widget.
  RefreshTokenStruct? userInfo;
  bool apiRequestCompleted = false;
  String? apiRequestLastUniqueKey;
  // State field(s) for TextField widget.
  final textFieldKey = GlobalKey();
  FocusNode? textFieldFocusNode;
  TextEditingController? textController;
  String? textFieldSelectedOption;
  String? Function(BuildContext, String?)? textControllerValidator;
  List<String> simpleSearchResults = [];
  // Models for CardTraining dynamic component.
  late FlutterFlowDynamicModels<CardTrainingModel> cardTrainingModels1;
  // Models for CardTraining dynamic component.
  late FlutterFlowDynamicModels<CardTrainingModel> cardTrainingModels2;

  @override
  void initState(BuildContext context) {
    cardTrainingModels1 = FlutterFlowDynamicModels(() => CardTrainingModel());
    cardTrainingModels2 = FlutterFlowDynamicModels(() => CardTrainingModel());
  }

  @override
  void dispose() {
    textFieldFocusNode?.dispose();

    cardTrainingModels1.dispose();
    cardTrainingModels2.dispose();
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
