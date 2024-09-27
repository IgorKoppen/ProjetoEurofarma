import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'alterar_senha_widget.dart' show AlterarSenhaWidget;
import 'package:flutter/material.dart';

class AlterarSenhaModel extends FlutterFlowModel<AlterarSenhaWidget> {
  ///  Local state fields for this page.

  bool senhaCorreta = true;

  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // State field(s) for SenhaAntiga widget.
  FocusNode? senhaAntigaFocusNode;
  TextEditingController? senhaAntigaTextController;
  late bool senhaAntigaVisibility;
  String? Function(BuildContext, String?)? senhaAntigaTextControllerValidator;
  String? _senhaAntigaTextControllerValidator(
      BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '06ze91fu' /* Campo necessário */,
      );
    }

    return null;
  }

  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode1;
  TextEditingController? textController2;
  late bool passwordVisibility1;
  String? Function(BuildContext, String?)? textController2Validator;
  String? _textController2Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'g5ggpx3n' /* Campo necessário */,
      );
    }

    if (val.length < 8) {
      return FFLocalizations.of(context).getText(
        '6em6eoyv' /* Pelo menos 8 letras */,
      );
    }

    if (!RegExp(
            '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$')
        .hasMatch(val)) {
      return FFLocalizations.of(context).getText(
        'onwiyanl' /* A senha precisa seguir as regr... */,
      );
    }
    return null;
  }

  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode2;
  TextEditingController? textController3;
  late bool passwordVisibility2;
  String? Function(BuildContext, String?)? textController3Validator;
  String? _textController3Validator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '1fmglrgl' /* Campo necessário */,
      );
    }

    if (val.length < 8) {
      return FFLocalizations.of(context).getText(
        'xxxsj6jh' /* Pelo menos 8 letras */,
      );
    }

    if (!RegExp(
            '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$')
        .hasMatch(val)) {
      return FFLocalizations.of(context).getText(
        '3zl0lp6e' /* A senha precisa seguir as regr... */,
      );
    }
    return null;
  }

  // Stores action output result for [Backend Call - API (UpdatePassworde)] action in Button widget.
  ApiCallResponse? apiResultq7q;

  @override
  void initState(BuildContext context) {
    senhaAntigaVisibility = false;
    senhaAntigaTextControllerValidator = _senhaAntigaTextControllerValidator;
    passwordVisibility1 = false;
    textController2Validator = _textController2Validator;
    passwordVisibility2 = false;
    textController3Validator = _textController3Validator;
  }

  @override
  void dispose() {
    senhaAntigaFocusNode?.dispose();
    senhaAntigaTextController?.dispose();

    textFieldFocusNode1?.dispose();
    textController2?.dispose();

    textFieldFocusNode2?.dispose();
    textController3?.dispose();
  }
}
