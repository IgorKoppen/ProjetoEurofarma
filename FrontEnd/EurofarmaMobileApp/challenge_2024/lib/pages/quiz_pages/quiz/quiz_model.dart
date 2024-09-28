import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/components/radio_button_quiz/radio_button_quiz_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'quiz_widget.dart' show QuizWidget;
import 'package:flutter/material.dart';

class QuizModel extends FlutterFlowModel<QuizWidget> {
  ///  Local state fields for this page.

  QuizStruct? quizInfo;
  void updateQuizInfoStruct(Function(QuizStruct) updateFn) {
    updateFn(quizInfo ??= QuizStruct());
  }

  int numeroDeTentativas = 1;

  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // Stores action output result for [Backend Call - API (findById)] action in Quiz widget.
  ApiCallResponse? apiResultju9;
  // Models for RadioButtonQuiz dynamic component.
  late FlutterFlowDynamicModels<RadioButtonQuizModel> radioButtonQuizModels;
  // Stores action output result for [Backend Call - API (validateQuiz)] action in Button widget.
  ApiCallResponse? apiResultajy;

  @override
  void initState(BuildContext context) {
    radioButtonQuizModels =
        FlutterFlowDynamicModels(() => RadioButtonQuizModel());
  }

  @override
  void dispose() {
    radioButtonQuizModels.dispose();
  }
}
