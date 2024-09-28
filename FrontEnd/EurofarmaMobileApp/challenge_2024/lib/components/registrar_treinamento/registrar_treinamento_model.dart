import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'registrar_treinamento_widget.dart' show RegistrarTreinamentoWidget;
import 'package:flutter/material.dart';

class RegistrarTreinamentoModel
    extends FlutterFlowModel<RegistrarTreinamentoWidget> {
  ///  State fields for stateful widgets in this component.

  final formKey = GlobalKey<FormState>();
  var codigoScanner = '';
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode;
  TextEditingController? textController;
  String? Function(BuildContext, String?)? textControllerValidator;
  String? _textControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '8o4a7yni' /* O código é obrigatório */,
      );
    }

    if (val.length < 10) {
      return FFLocalizations.of(context).getText(
        'nu0vx73w' /* O codigo possui 10 caracteres */,
      );
    }
    if (val.length > 10) {
      return FFLocalizations.of(context).getText(
        '46zntdsl' /* O codigo possui 10 caracteres */,
      );
    }

    return null;
  }

  // Stores action output result for [Backend Call - API (getRoomByCode)] action in Button widget.
  ApiCallResponse? findRoomByCode;

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
