import '/backend/api_requests/api_calls.dart';
import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'chatbot_page_widget.dart' show ChatbotPageWidget;
import 'package:flutter/material.dart';

class ChatbotPageModel extends FlutterFlowModel<ChatbotPageWidget> {
  ///  Local state fields for this page.

  String? resposta;

  List<HistoricoConversaStruct> historicoConversa = [];
  void addToHistoricoConversa(HistoricoConversaStruct item) =>
      historicoConversa.add(item);
  void removeFromHistoricoConversa(HistoricoConversaStruct item) =>
      historicoConversa.remove(item);
  void removeAtIndexFromHistoricoConversa(int index) =>
      historicoConversa.removeAt(index);
  void insertAtIndexInHistoricoConversa(
          int index, HistoricoConversaStruct item) =>
      historicoConversa.insert(index, item);
  void updateHistoricoConversaAtIndex(
          int index, Function(HistoricoConversaStruct) updateFn) =>
      historicoConversa[index] = updateFn(historicoConversa[index]);

  ///  State fields for stateful widgets in this page.

  final formKey = GlobalKey<FormState>();
  // State field(s) for TextField widget.
  FocusNode? textFieldFocusNode;
  TextEditingController? textController;
  String? Function(BuildContext, String?)? textControllerValidator;
  String? _textControllerValidator(BuildContext context, String? val) {
    if (val == null || val.isEmpty) {
      return FFLocalizations.of(context).getText(
        'kfmjjuwj' /* É necessário uma mensagem */,
      );
    }

    if (val.length < 10) {
      return FFLocalizations.of(context).getText(
        '08umpjfs' /* É necessário no minimo de 10 c... */,
      );
    }
    if (val.length > 300) {
      return FFLocalizations.of(context).getText(
        'kbr6jnbg' /* Messagem máxima excedida */,
      );
    }

    return null;
  }

  // Stores action output result for [Backend Call - API (ChatBotEurinho)] action in IconButton widget.
  ApiCallResponse? apiResult78s;

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
