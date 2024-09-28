import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'create_quiz_widget.dart' show CreateQuizWidget;
import 'package:flutter/material.dart';

class CreateQuizModel extends FlutterFlowModel<CreateQuizWidget> {
  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode1;
  TextEditingController? textController1;
  String? Function(BuildContext, String?)? textController1Validator;
  String? _textController1Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'gt6j9y7i' /* Campo requerido */,
      );
    }

    if (val.length < 5) {
      return FFLocalizations.of(context).getText(
        'rxtmmx1v' /* Minimo 5 caracteres */,
      );
    }

    return null;
  }

  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode2;
  TextEditingController? textController2;
  String? Function(BuildContext, String?)? textController2Validator;
  String? _textController2Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'w6bc8qjg' /* Campo requerido */,
      );
    }

    if (val.length < 5) {
      return FFLocalizations.of(context).getText(
        '3b2bqdy5' /* Minimo 5 caracteres */,
      );
    }

    return null;
  }

  // State field(s) for Slider widget.
  double? sliderValue;
  // State field(s) for CountController widget.
  int? countControllerValue;
  // Stores action output result for [Backend Call - API (insertQuiz)] action in Button widget.
  ApiCallResponse? apiResult9t3;

  @override
  void initState(BuildContext context) {
    textController1Validator = _textController1Validator;
    textController2Validator = _textController2Validator;
  }

  @override
  void dispose() {
    textFieldFocusNode1?.dispose();
    textController1?.dispose();

    textFieldFocusNode2?.dispose();
    textController2?.dispose();
  }
}
