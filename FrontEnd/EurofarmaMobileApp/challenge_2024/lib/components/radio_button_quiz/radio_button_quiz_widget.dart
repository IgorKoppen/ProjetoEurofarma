import '/flutter_flow/flutter_flow_radio_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/form_field_controller.dart';
import 'package:flutter/material.dart';
import 'radio_button_quiz_model.dart';
export 'radio_button_quiz_model.dart';

class RadioButtonQuizWidget extends StatefulWidget {
  const RadioButtonQuizWidget({
    super.key,
    this.parameter1,
  });

  final List<String>? parameter1;

  @override
  State<RadioButtonQuizWidget> createState() => _RadioButtonQuizWidgetState();
}

class _RadioButtonQuizWidgetState extends State<RadioButtonQuizWidget> {
  late RadioButtonQuizModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => RadioButtonQuizModel());

    WidgetsBinding.instance.addPostFrameCallback((_) => safeSetState(() {}));
  }

  @override
  void dispose() {
    _model.maybeDispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return FlutterFlowRadioButton(
      options: widget.parameter1!.toList(),
      onChanged: (val) => safeSetState(() {}),
      controller: _model.radioButtonValueController ??=
          FormFieldController<String>(null),
      optionHeight: 75.0,
      optionWidth: MediaQuery.sizeOf(context).width * 0.9,
      textStyle: FlutterFlowTheme.of(context).labelMedium.override(
            fontFamily: 'Readex Pro',
            color: FlutterFlowTheme.of(context).primaryText,
            letterSpacing: 0.0,
          ),
      buttonPosition: RadioButtonPosition.left,
      direction: Axis.vertical,
      radioButtonColor: FlutterFlowTheme.of(context).secondary,
      inactiveRadioButtonColor: FlutterFlowTheme.of(context).alternate,
      toggleable: false,
      horizontalAlignment: WrapAlignment.start,
      verticalAlignment: WrapCrossAlignment.start,
    );
  }
}
