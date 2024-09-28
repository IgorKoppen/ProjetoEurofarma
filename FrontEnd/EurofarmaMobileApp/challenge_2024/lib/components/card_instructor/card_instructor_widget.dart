import '/backend/schema/structs/index.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/custom_functions.dart' as functions;
import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'card_instructor_model.dart';
export 'card_instructor_model.dart';

class CardInstructorWidget extends StatefulWidget {
  const CardInstructorWidget({
    super.key,
    required this.trainingInfo,
  });

  final TrainingInstructorStruct? trainingInfo;

  @override
  State<CardInstructorWidget> createState() => _CardInstructorWidgetState();
}

class _CardInstructorWidgetState extends State<CardInstructorWidget> {
  late CardInstructorModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => CardInstructorModel());

    WidgetsBinding.instance.addPostFrameCallback((_) => safeSetState(() {}));
  }

  @override
  void dispose() {
    _model.maybeDispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsetsDirectional.fromSTEB(0.0, 0.0, 0.0, 20.0),
      child: InkWell(
        splashColor: Colors.transparent,
        focusColor: Colors.transparent,
        hoverColor: Colors.transparent,
        highlightColor: Colors.transparent,
        onDoubleTap: () async {
          context.pushNamed(
            'EditInfo',
            queryParameters: {
              'treinamentoInfo': serializeParam(
                widget.trainingInfo,
                ParamType.DataStruct,
              ),
            }.withoutNulls,
          );
        },
        child: Container(
          width: MediaQuery.sizeOf(context).width * 1.0,
          height: 130.0,
          decoration: BoxDecoration(
            color: FlutterFlowTheme.of(context).secondaryBackground,
            borderRadius: const BorderRadius.only(
              bottomLeft: Radius.circular(15.0),
              bottomRight: Radius.circular(15.0),
              topLeft: Radius.circular(15.0),
              topRight: Radius.circular(15.0),
            ),
            border: Border.all(
              color: FlutterFlowTheme.of(context).secondary,
            ),
          ),
          child: Padding(
            padding: const EdgeInsetsDirectional.fromSTEB(10.0, 0.0, 10.0, 0.0),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsetsDirectional.fromSTEB(0.0, 10.0, 0.0, 0.0),
                  child: Row(
                    mainAxisSize: MainAxisSize.max,
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        valueOrDefault<String>(
                          widget.trainingInfo?.name,
                          'Nome',
                        ).maybeHandleOverflow(
                          maxChars: 20,
                          replacement: '…',
                        ),
                        style: FlutterFlowTheme.of(context).bodyMedium.override(
                              fontFamily: 'Readex Pro',
                              color: FlutterFlowTheme.of(context).primaryText,
                              fontSize: 15.0,
                              letterSpacing: 0.0,
                              fontWeight: FontWeight.w600,
                            ),
                      ),
                      Container(
                        width: 90.0,
                        height: 30.0,
                        decoration: BoxDecoration(
                          color: functions
                                  .dateIsPast(widget.trainingInfo!.closingDate)
                              ? const Color(0xFFB00B1E)
                              : const Color(0xFF4BE75D),
                          borderRadius: BorderRadius.circular(15.0),
                        ),
                        alignment: const AlignmentDirectional(0.0, 0.0),
                        child: Align(
                          alignment: const AlignmentDirectional(0.0, 0.0),
                          child: Text(
                            functions.dateIsPast(
                                    widget.trainingInfo!.closingDate)
                                ? 'Finalizado'
                                : 'Aberto',
                            style: FlutterFlowTheme.of(context)
                                .bodyMedium
                                .override(
                                  fontFamily: 'Readex Pro',
                                  color: functions.dateIsPast(
                                          widget.trainingInfo!.closingDate)
                                      ? const Color(0xFF331212)
                                      : const Color(0xFF0A580E),
                                  fontSize: 14.0,
                                  letterSpacing: 0.0,
                                  fontWeight: FontWeight.bold,
                                ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Text(
                  valueOrDefault<String>(
                    functions.formatDate(widget.trainingInfo?.closingDate),
                    'null',
                  ),
                  style: FlutterFlowTheme.of(context).bodyMedium.override(
                        fontFamily: 'Readex Pro',
                        color: FlutterFlowTheme.of(context).secondaryText,
                        fontSize: 13.0,
                        letterSpacing: 0.0,
                      ),
                ),
                Align(
                  alignment: const AlignmentDirectional(0.0, 0.0),
                  child: Padding(
                    padding:
                        const EdgeInsetsDirectional.fromSTEB(0.0, 18.0, 0.0, 10.0),
                    child: Row(
                      mainAxisSize: MainAxisSize.max,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Align(
                          alignment: const AlignmentDirectional(0.0, 0.0),
                          child: Builder(
                            builder: (context) {
                              final tags = (widget.trainingInfo?.tags
                                          .map((e) => e)
                                          .toList()
                                          .toList() ??
                                      [])
                                  .take(3)
                                  .toList();

                              return Wrap(
                                spacing: 7.0,
                                runSpacing: 7.0,
                                alignment: WrapAlignment.start,
                                crossAxisAlignment: WrapCrossAlignment.start,
                                direction: Axis.horizontal,
                                runAlignment: WrapAlignment.start,
                                verticalDirection: VerticalDirection.down,
                                clipBehavior: Clip.none,
                                children:
                                    List.generate(tags.length, (tagsIndex) {
                                  final tagsItem = tags[tagsIndex];
                                  return Align(
                                    alignment: const AlignmentDirectional(0.0, 0.0),
                                    child: Container(
                                      width: 60.0,
                                      height: 30.0,
                                      decoration: BoxDecoration(
                                        color: colorFromCssString(
                                          valueOrDefault<String>(
                                            tagsItem.color,
                                            '#00358E',
                                          ),
                                          defaultColor: const Color(0xFF00358E),
                                        ),
                                        borderRadius:
                                            BorderRadius.circular(15.0),
                                      ),
                                      alignment: const AlignmentDirectional(0.0, 0.0),
                                      child: Align(
                                        alignment:
                                            const AlignmentDirectional(0.0, 0.0),
                                        child: SelectionArea(
                                            child: AutoSizeText(
                                          valueOrDefault<String>(
                                            tagsItem.name,
                                            'Nome',
                                          ).maybeHandleOverflow(
                                            maxChars: 12,
                                            replacement: '…',
                                          ),
                                          textAlign: TextAlign.center,
                                          maxLines: 1,
                                          minFontSize: 7.0,
                                          style: FlutterFlowTheme.of(context)
                                              .titleMedium
                                              .override(
                                                fontFamily: 'Readex Pro',
                                                color: colorFromCssString(
                                                  functions
                                                      .checkHexColorBrightness(
                                                          tagsItem.color),
                                                  defaultColor: Colors.black,
                                                ),
                                                fontSize: 11.0,
                                                letterSpacing: 1.0,
                                                fontWeight: FontWeight.bold,
                                              ),
                                        )),
                                      ),
                                    ),
                                  );
                                }),
                              );
                            },
                          ),
                        ),
                        FlutterFlowIconButton(
                          borderColor: Colors.transparent,
                          borderRadius: 20.0,
                          borderWidth: 1.0,
                          buttonSize: 40.0,
                          icon: Icon(
                            Icons.open_in_full_outlined,
                            color: FlutterFlowTheme.of(context).buttons,
                            size: 24.0,
                          ),
                          onPressed: () async {
                            context.pushNamed(
                              'EditInfo',
                              queryParameters: {
                                'treinamentoInfo': serializeParam(
                                  widget.trainingInfo,
                                  ParamType.DataStruct,
                                ),
                              }.withoutNulls,
                            );
                          },
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
