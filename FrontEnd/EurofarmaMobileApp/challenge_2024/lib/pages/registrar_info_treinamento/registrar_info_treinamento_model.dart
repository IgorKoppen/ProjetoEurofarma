import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'registrar_info_treinamento_widget.dart'
    show RegistrarInfoTreinamentoWidget;
import 'package:flutter/material.dart';

class RegistrarInfoTreinamentoModel
    extends FlutterFlowModel<RegistrarInfoTreinamentoWidget> {
  ///  Local state fields for this page.

  String? title;

  String? description;

  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // State field(s) for senha widget.
  FocusNode? senhaFocusNode;
  TextEditingController? senhaTextController;
  String? Function(BuildContext, String?)? senhaTextControllerValidator;
  String? _senhaTextControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '8m36vgxy' /* Senha é necessária! */,
      );
    }

    if (val.length < 10) {
      return FFLocalizations.of(context).getText(
        '1a683220' /* Mínimo de characteres é 10 */,
      );
    }
    if (val.length > 10) {
      return FFLocalizations.of(context).getText(
        'uy8x988r' /* Máximo de characteres é 10 */,
      );
    }

    return null;
  }

  // Stores action output result for [Backend Call - API (confirmRoomPassword)] action in Button widget.
  ApiCallResponse? confirmPassword;

  @override
  void initState(BuildContext context) {
    senhaTextControllerValidator = _senhaTextControllerValidator;
  }

  @override
  void dispose() {
    senhaFocusNode?.dispose();
    senhaTextController?.dispose();
  }
}
