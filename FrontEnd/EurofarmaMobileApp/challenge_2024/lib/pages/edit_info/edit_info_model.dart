import '/components/treinamento_edit/treinamento_edit_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'edit_info_widget.dart' show EditInfoWidget;
import 'package:flutter/material.dart';

class EditInfoModel extends FlutterFlowModel<EditInfoWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for TreinamentoEdit component.
  late TreinamentoEditModel treinamentoEditModel;

  @override
  void initState(BuildContext context) {
    treinamentoEditModel = createModel(context, () => TreinamentoEditModel());
  }

  @override
  void dispose() {
    treinamentoEditModel.dispose();
  }
}
