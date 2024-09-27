import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'criar_tag_widget.dart' show CriarTagWidget;
import 'package:flutter/material.dart';

class CriarTagModel extends FlutterFlowModel<CriarTagWidget> {
  ///  State fields for stateful widgets in this component.

  final formKey = GlobalKey<FormState>();
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode;
  TextEditingController? textController;
  String? Function(BuildContext, String?)? textControllerValidator;
  String? _textControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'y0w0yepg' /* Campo necessário */,
      );
    }

    if (val.length < 3) {
      return FFLocalizations.of(context).getText(
        '8gw4w2dq' /* A tag deve conter no mínimo 3 ... */,
      );
    }
    if (val.length > 20) {
      return FFLocalizations.of(context).getText(
        'u11t7nkn' /* A tag deve conter no máximo 20... */,
      );
    }
    if (!RegExp('^[A-Za-z\\d]*\$').hasMatch(val)) {
      return FFLocalizations.of(context).getText(
        'zmagfg7d' /* A tag só pode conter letras nú... */,
      );
    }
    return null;
  }

  Color? colorPicked;
  // Stores action output result for [Backend Call - API (InsertTag)] action in Button widget.
  ApiCallResponse? apiResult2on;

  @override
  void initState(BuildContext context) {
    textControllerValidator = _textControllerValidator;
  }

  @override
  void dispose() {
    textFieldFocusNode?.dispose();
    textController?.dispose();
  }
}
