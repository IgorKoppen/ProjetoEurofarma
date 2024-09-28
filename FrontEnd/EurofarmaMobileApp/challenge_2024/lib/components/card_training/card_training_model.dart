import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'card_training_widget.dart' show CardTrainingWidget;
import 'package:flutter/material.dart';

class CardTrainingModel extends FlutterFlowModel<CardTrainingWidget> {
  ///  Local state fields for this component.

  TrainingStruct? training;
  void updateTrainingStruct(Function(TrainingStruct) updateFn) {
    updateFn(training ??= TrainingStruct());
  }

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {}
}
