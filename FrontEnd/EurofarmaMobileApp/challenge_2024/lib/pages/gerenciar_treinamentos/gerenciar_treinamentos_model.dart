import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/components/card_instructor/card_instructor_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'dart:async';
import 'gerenciar_treinamentos_widget.dart' show GerenciarTreinamentosWidget;
import 'package:flutter/material.dart';

class GerenciarTreinamentosModel
    extends FlutterFlowModel<GerenciarTreinamentosWidget> {
  ///  Local state fields for this page.

  int searchActive = 0;

  String? tagFiltered;

  List<TagsStruct> sdDDZ = [];
  void addToSdDDZ(TagsStruct item) => sdDDZ.add(item);
  void removeFromSdDDZ(TagsStruct item) => sdDDZ.remove(item);
  void removeAtIndexFromSdDDZ(int index) => sdDDZ.removeAt(index);
  void insertAtIndexInSdDDZ(int index, TagsStruct item) =>
      sdDDZ.insert(index, item);
  void updateSdDDZAtIndex(int index, Function(TagsStruct) updateFn) =>
      sdDDZ[index] = updateFn(sdDDZ[index]);

  ///  State fields for stateful widgets in this page.

  // State field(s) for TextField widget.
  final textFieldKey = GlobalKey();
  FocusNode? textFieldFocusNode;
  TextEditingController? textController;
  String? textFieldSelectedOption;
  String? Function(BuildContext, String?)? textControllerValidator;
  List<String> simpleSearchResults1 = [];
  List<String> simpleSearchResults2 = [];
  Completer<ApiCallResponse>? apiRequestCompleter;
  // Models for CardInstructor dynamic component.
  late FlutterFlowDynamicModels<CardInstructorModel> cardInstructorModels1;
  // Models for CardInstructor dynamic component.
  late FlutterFlowDynamicModels<CardInstructorModel> cardInstructorModels2;
  // Models for CardInstructor dynamic component.
  late FlutterFlowDynamicModels<CardInstructorModel> cardInstructorModels3;

  @override
  void initState(BuildContext context) {
    cardInstructorModels1 =
        FlutterFlowDynamicModels(() => CardInstructorModel());
    cardInstructorModels2 =
        FlutterFlowDynamicModels(() => CardInstructorModel());
    cardInstructorModels3 =
        FlutterFlowDynamicModels(() => CardInstructorModel());
  }

  @override
  void dispose() {
    textFieldFocusNode?.dispose();

    cardInstructorModels1.dispose();
    cardInstructorModels2.dispose();
    cardInstructorModels3.dispose();
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
      final requestComplete = apiRequestCompleter?.isCompleted ?? false;
      if (timeElapsed > maxWait || (requestComplete && timeElapsed > minWait)) {
        break;
      }
    }
  }
}
