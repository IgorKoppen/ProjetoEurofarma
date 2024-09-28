import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'edit_question_widget.dart' show EditQuestionWidget;
import 'package:flutter/material.dart';

class EditQuestionModel extends FlutterFlowModel<EditQuestionWidget> {
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

  QuestionsStruct? questionList;
  void updateQuestionListStruct(Function(QuestionsStruct) updateFn) {
    updateFn(questionList ??= QuestionsStruct());
  }

  int counterAUX = 0;

  ///  State fields for stateful widgets in this page.

  final formKey2 = GlobalKey<FormState>();
  final formKey1 = GlobalKey<FormState>();
  // Stores action output result for [Backend Call - API (findByIdQuestion)] action in EditQuestion widget.
  ApiCallResponse? apiResultl1h;
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode;
  TextEditingController? textController1;
  String? Function(BuildContext, String?)? textController1Validator;
  String? _textController1Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'w8w9txrz' /* Campo necessário */,
      );
    }

    if (val.length > 400) {
      return FFLocalizations.of(context).getText(
        'k5znp6ai' /* Máximo 400 caracteres */,
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
        'frmk2o24' /* Adicione uma opção */,
      );
    }

    return null;
  }

  // Stores action output result for [Backend Call - API (updateAll)] action in Button widget.
  ApiCallResponse? insertAnswer;
  // Stores action output result for [Backend Call - API (updateQuestion)] action in Button widget.
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
