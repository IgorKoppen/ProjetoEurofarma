import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'create_new_question_widget.dart' show CreateNewQuestionWidget;
import 'package:flutter/material.dart';

class CreateNewQuestionModel extends FlutterFlowModel<CreateNewQuestionWidget> {
  ///  Local state fields for this page.

  String? correctAnswer;

  List<OpcoesQuizStruct> quesitonOptions = [];
  void addToQuesitonOptions(OpcoesQuizStruct item) => quesitonOptions.add(item);
  void removeFromQuesitonOptions(OpcoesQuizStruct item) =>
      quesitonOptions.remove(item);
  void removeAtIndexFromQuesitonOptions(int index) =>
      quesitonOptions.removeAt(index);
  void insertAtIndexInQuesitonOptions(int index, OpcoesQuizStruct item) =>
      quesitonOptions.insert(index, item);
  void updateQuesitonOptionsAtIndex(
          int index, Function(OpcoesQuizStruct) updateFn) =>
      quesitonOptions[index] = updateFn(quesitonOptions[index]);

  int? optionCounter = 0;

  ///  State fields for stateful widgets in this page.

  final formKey2 = GlobalKey<FormState>();
  final formKey1 = GlobalKey<FormState>();
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode;
  TextEditingController? textController1;
  String? Function(BuildContext, String?)? textController1Validator;
  String? _textController1Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '2yfdzy41' /* Campo necessário */,
      );
    }

    if (val.length > 400) {
      return FFLocalizations.of(context).getText(
        'gmac8bru' /* Máximo 400 caracteres */,
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
        'mw5fscxy' /* Adicione uma resposta */,
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
