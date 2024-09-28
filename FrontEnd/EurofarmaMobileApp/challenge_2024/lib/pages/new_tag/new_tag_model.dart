import '/components/criar_tag/criar_tag_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'new_tag_widget.dart' show NewTagWidget;
import 'package:flutter/material.dart';

class NewTagModel extends FlutterFlowModel<NewTagWidget> {
  ///  State fields for stateful widgets in this page.

  // Model for CriarTag component.
  late CriarTagModel criarTagModel;

  @override
  void initState(BuildContext context) {
    criarTagModel = createModel(context, () => CriarTagModel());
  }

  @override
  void dispose() {
    criarTagModel.dispose();
  }
}
