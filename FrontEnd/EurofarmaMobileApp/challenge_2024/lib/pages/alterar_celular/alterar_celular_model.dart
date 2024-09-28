import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'alterar_celular_widget.dart' show AlterarCelularWidget;
import 'package:flutter/material.dart';
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';

class AlterarCelularModel extends FlutterFlowModel<AlterarCelularWidget> {
  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // State field(s) for Senha widget.
  FocusNode? senhaFocusNode;
  TextEditingController? senhaTextController;
  late bool senhaVisibility;
  String? Function(BuildContext, String?)? senhaTextControllerValidator;
  String? _senhaTextControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        '3onv3vrs' /* Campo necessário */,
      );
    }

    return null;
  }

  // State field(s) for CellPhone widget.
  FocusNode? cellPhoneFocusNode;
  TextEditingController? cellPhoneTextController;
  final cellPhoneMask = MaskTextInputFormatter(mask: '+## (##) #####-####');
  String? Function(BuildContext, String?)? cellPhoneTextControllerValidator;
  String? _cellPhoneTextControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'fe2gwqj0' /* Campo necessário */,
      );
    }

    if (val.length < 19) {
      return FFLocalizations.of(context).getText(
        'nfrks0jt' /* São necessários 13 digitos */,
      );
    }
    if (val.length > 19) {
      return FFLocalizations.of(context).getText(
        'sxx10j62' /* São necessários 13 digitos */,
      );
    }

    return null;
  }

  // Stores action output result for [Backend Call - API (UpdateCellPhone)] action in Button widget.
  ApiCallResponse? changeCellPhone;

  @override
  void initState(BuildContext context) {
    senhaVisibility = false;
    senhaTextControllerValidator = _senhaTextControllerValidator;
    cellPhoneTextControllerValidator = _cellPhoneTextControllerValidator;
  }

  @override
  void dispose() {
    senhaFocusNode?.dispose();
    senhaTextController?.dispose();

    cellPhoneFocusNode?.dispose();
    cellPhoneTextController?.dispose();
  }
}
