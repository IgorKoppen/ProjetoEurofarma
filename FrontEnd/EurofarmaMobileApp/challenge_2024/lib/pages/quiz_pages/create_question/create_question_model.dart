import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'create_question_widget.dart' show CreateQuestionWidget;
import 'package:flutter/material.dart';

class CreateQuestionModel extends FlutterFlowModel<CreateQuestionWidget> {
  ///  Local state fields for this page.

  int optionCounter = 0;

  List<OpcoesQuizStruct> questionOptions = [];
  void addToQuestionOptions(OpcoesQuizStruct item) => questionOptions.add(item);
  void removeFromQuestionOptions(OpcoesQuizStruct item) =>
      questionOptions.remove(item);
  void removeAtIndexFromQuestionOptions(int index) =>
      questionOptions.removeAt(index);
  void insertAtIndexInQuestionOptions(int index, OpcoesQuizStruct item) =>
      questionOptions.insert(index, item);
  void updateQuestionOptionsAtIndex(
          int index, Function(OpcoesQuizStruct) updateFn) =>
      questionOptions[index] = updateFn(questionOptions[index]);

  String? correctAnswer = '';

  int questionNumber = 0;

  int questionCounter = 0;

  ///  State fields for stateful widgets in this page.

  final formKey1 = GlobalKey<FormState>();
  final formKey2 = GlobalKey<FormState>();
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode;
  TextEditingController? textController1;
  String? Function(BuildContext, String?)? textController1Validator;
  String? _textController1Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '0k5z5bc0' /* Campo necessário */,
      );
    }

    if (val.length < 5) {
      return FFLocalizations.of(context).getText(
        '5k2hn6l8' /* Mínimo 5 caracteres */,
      );
    }
    if (val.length > 400) {
      return FFLocalizations.of(context).getText(
        't85nw6iv' /* Máximo 400 caracteres */,
      );
    }

    return null;
  }

  // State field(s) for optionName widget.
  FocusNode? optionNameFocusNode;
  TextEditingController? optionNameTextController;
  String? Function(BuildContext, String?)? optionNameTextControllerValidator;
  String? _optionNameTextControllerValidator(
      BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'ohyg4wxe' /* Please add an option name... */,
      );
    }

    return null;
  }

  // Stores action output result for [Backend Call - API (insertAll)] action in Button widget.
  ApiCallResponse? insertAnswer;
  // Stores action output result for [Backend Call - API (insertQuestion)] action in Button widget.
  ApiCallResponse? apiResultxwz;

  @override
  void initState(BuildContext context) {
    textController1Validator = _textController1Validator;
    optionNameTextControllerValidator = _optionNameTextControllerValidator;
  }

  @override
  void dispose() {
    textFieldFocusNode?.dispose();
    textController1?.dispose();

    optionNameFocusNode?.dispose();
    optionNameTextController?.dispose();
  }
}
