import '/components/criar_tag/criar_tag_widget.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'package:flutter/material.dart';
import 'new_tag_model.dart';
export 'new_tag_model.dart';

class NewTagWidget extends StatefulWidget {
  const NewTagWidget({super.key});

  @override
  State<NewTagWidget> createState() => _NewTagWidgetState();
}

class _NewTagWidgetState extends State<NewTagWidget> {
  late NewTagModel _model;

  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => NewTagModel());

    WidgetsBinding.instance.addPostFrameCallback((_) => safeSetState(() {}));
  }

  @override
  void dispose() {
    _model.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => FocusScope.of(context).unfocus(),
      child: Scaffold(
        key: scaffoldKey,
        backgroundColor: FlutterFlowTheme.of(context).secondaryBackground,
        body: SafeArea(
          top: true,
          child: wrapWithModel(
            model: _model.criarTagModel,
            updateCallback: () => safeSetState(() {}),
            child: const CriarTagWidget(),
          ),
        ),
      ),
    );
  }
}
