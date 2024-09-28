import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'card_instructor_widget.dart' show CardInstructorWidget;
import 'package:flutter/material.dart';

class CardInstructorModel extends FlutterFlowModel<CardInstructorWidget> {
  ///  Local state fields for this component.

  TrainingInstructorStruct? dataTraining;
  void updateDataTrainingStruct(Function(TrainingInstructorStruct) updateFn) {
    updateFn(dataTraining ??= TrainingInstructorStruct());
  }

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {}
}
