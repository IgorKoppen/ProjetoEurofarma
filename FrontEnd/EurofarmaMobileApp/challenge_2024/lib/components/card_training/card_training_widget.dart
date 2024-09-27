import '/backend/schema/structs/index.dart';
import '/components/treinamento_info/treinamento_info_widget.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/custom_functions.dart' as functions;
import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'card_training_model.dart';
export 'card_training_model.dart';

class CardTrainingWidget extends StatefulWidget {
  const CardTrainingWidget({
    super.key,
    required this.trainingInfo,
  });

  final TrainingStruct? trainingInfo;

  @override
  State<CardTrainingWidget> createState() => _CardTrainingWidgetState();
}

class _CardTrainingWidgetState extends State<CardTrainingWidget> {
  late CardTrainingModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => CardTrainingModel());

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
          await showModalBottomSheet(
            isScrollControlled: true,
            backgroundColor: Colors.transparent,
            enableDrag: false,
            context: context,
            builder: (context) {
              return Padding(
                padding: MediaQuery.viewInsetsOf(context),
                child: SizedBox(
                  height: MediaQuery.sizeOf(context).height * 0.95,
                  child: TreinamentoInfoWidget(
                    trainingInfo: widget.trainingInfo!,
                  ),
                ),
              );
            },
          ).then((value) => safeSetState(() {}));
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
                          'Nenhum',
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
                          color: const Color(0xFF4BE75D),
                          borderRadius: BorderRadius.circular(15.0),
                        ),
                        alignment: const AlignmentDirectional(0.0, 0.0),
                        child: Align(
                          alignment: const AlignmentDirectional(0.0, 0.0),
                          child: Text(
                            FFLocalizations.of(context).getText(
                              'grlwqfyu' /* Concluido */,
                            ),
                            style: FlutterFlowTheme.of(context)
                                .bodyMedium
                                .override(
                                  fontFamily: 'Readex Pro',
                                  color: const Color(0xFF0A580E),
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
                    functions
                        .formatDate(widget.trainingInfo?.registrationDate),
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
                                            'name',
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
                            await showModalBottomSheet(
                              isScrollControlled: true,
                              backgroundColor: Colors.transparent,
                              enableDrag: false,
                              context: context,
                              builder: (context) {
                                return Padding(
                                  padding: MediaQuery.viewInsetsOf(context),
                                  child: SizedBox(
                                    height: MediaQuery.sizeOf(context).height *
                                        0.95,
                                    child: TreinamentoInfoWidget(
                                      trainingInfo: widget.trainingInfo!,
                                    ),
                                  ),
                                );
                              },
                            ).then((value) => safeSetState(() {}));
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
