import '/auth/custom_auth/auth_util.dart';
import '/backend/api_requests/api_calls.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'package:flutter/material.dart';
import 'package:flutter_barcode_scanner/flutter_barcode_scanner.dart';
import 'registrar_treinamento_model.dart';
export 'registrar_treinamento_model.dart';

class RegistrarTreinamentoWidget extends StatefulWidget {
  const RegistrarTreinamentoWidget({super.key});

  @override
  State<RegistrarTreinamentoWidget> createState() =>
      _RegistrarTreinamentoWidgetState();
}

class _RegistrarTreinamentoWidgetState
    extends State<RegistrarTreinamentoWidget> {
  late RegistrarTreinamentoModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => RegistrarTreinamentoModel());

    _model.textController ??= TextEditingController();
    _model.textFieldFocusNode ??= FocusNode();

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
      padding: const EdgeInsetsDirectional.fromSTEB(10.0, 0.0, 10.0, 0.0),
      child: Stack(
        children: [
          InkWell(
            splashColor: Colors.transparent,
            focusColor: Colors.transparent,
            hoverColor: Colors.transparent,
            highlightColor: Colors.transparent,
            onTap: () async {
              Navigator.pop(context);
            },
            child: Container(
              width: double.infinity,
              height: double.infinity,
              decoration: const BoxDecoration(
                color: Color(0x0014181B),
              ),
            ),
          ),
          Align(
            alignment: const AlignmentDirectional(0.0, 0.0),
            child: Container(
              width: double.infinity,
              height: 320.0,
              decoration: BoxDecoration(
                color: FlutterFlowTheme.of(context).secondaryBackground,
                borderRadius: BorderRadius.circular(16.0),
              ),
              child: Align(
                alignment: const AlignmentDirectional(0.0, 0.0),
                child: Column(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Align(
                      alignment: const AlignmentDirectional(1.0, -1.0),
                      child: FlutterFlowIconButton(
                        borderColor: Colors.transparent,
                        borderRadius: 20.0,
                        borderWidth: 1.0,
                        buttonSize: 48.0,
                        icon: Icon(
                          Icons.close,
                          color: FlutterFlowTheme.of(context).buttons,
                          size: 24.0,
                        ),
                        onPressed: () async {
                          context.safePop();
                        },
                      ),
                    ),
                    Align(
                      alignment: const AlignmentDirectional(-1.0, 0.0),
                      child: Padding(
                        padding:
                            const EdgeInsetsDirectional.fromSTEB(15.0, 0.0, 0.0, 0.0),
                        child: Text(
                          FFLocalizations.of(context).getText(
                            'vj5w0k5h' /* Registre seu novo treinamento */,
                          ),
                          style: FlutterFlowTheme.of(context)
                              .bodyMedium
                              .override(
                                fontFamily: 'Readex Pro',
                                color: FlutterFlowTheme.of(context).primaryText,
                                fontSize: 20.0,
                                letterSpacing: 0.0,
                              ),
                        ),
                      ),
                    ),
                    Align(
                      alignment: const AlignmentDirectional(-1.0, 0.0),
                      child: Padding(
                        padding:
                            const EdgeInsetsDirectional.fromSTEB(15.0, 8.0, 0.0, 0.0),
                        child: Text(
                          FFLocalizations.of(context).getText(
                            'frfvxdrt' /* Escaneie o QR Code ou insira o... */,
                          ),
                          style: FlutterFlowTheme.of(context)
                              .bodyMedium
                              .override(
                                fontFamily: 'Readex Pro',
                                color:
                                    FlutterFlowTheme.of(context).secondaryText,
                                fontSize: 12.0,
                                letterSpacing: 0.0,
                              ),
                        ),
                      ),
                    ),
                    if (!isWeb &&
                        responsiveVisibility(
                          context: context,
                          desktop: false,
                        ))
                      Padding(
                        padding:
                            const EdgeInsetsDirectional.fromSTEB(0.0, 15.0, 0.0, 0.0),
                        child: FFButtonWidget(
                          onPressed: () async {
                            _model.codigoScanner =
                                await FlutterBarcodeScanner.scanBarcode(
                              '#C62828', // scanning line color
                              FFLocalizations.of(context).getText(
                                'fpm5fqwu' /* Cancelar */,
                              ), // cancel button text
                              true, // whether to show the flash icon
                              ScanMode.QR,
                            );

                            safeSetState(() {
                              _model.textController?.text =
                                  _model.codigoScanner;
                              _model.textController?.selection =
                                  TextSelection.collapsed(
                                      offset:
                                          _model.textController!.text.length);
                            });

                            safeSetState(() {});
                          },
                          text: FFLocalizations.of(context).getText(
                            '5a5cwm7p' /* Scannear QR Code */,
                          ),
                          icon: const Icon(
                            Icons.qr_code_scanner,
                            size: 28.0,
                          ),
                          options: FFButtonOptions(
                            height: 40.0,
                            padding: const EdgeInsetsDirectional.fromSTEB(
                                24.0, 0.0, 24.0, 0.0),
                            iconPadding: const EdgeInsetsDirectional.fromSTEB(
                                0.0, 0.0, 0.0, 0.0),
                            color: FlutterFlowTheme.of(context).secondary,
                            textStyle: FlutterFlowTheme.of(context)
                                .titleSmall
                                .override(
                                  fontFamily: 'Readex Pro',
                                  color: FlutterFlowTheme.of(context).info,
                                  letterSpacing: 0.0,
                                ),
                            elevation: 3.0,
                            borderSide: const BorderSide(
                              color: Colors.transparent,
                              width: 1.0,
                            ),
                            borderRadius: BorderRadius.circular(15.0),
                          ),
                        ),
                      ),
                    Form(
                      key: _model.formKey,
                      autovalidateMode: AutovalidateMode.disabled,
                      child: Column(
                        mainAxisSize: MainAxisSize.max,
                        children: [
                          Padding(
                            padding: const EdgeInsetsDirectional.fromSTEB(
                                40.0, 20.0, 40.0, 0.0),
                            child: TextFormField(
                              controller: _model.textController,
                              focusNode: _model.textFieldFocusNode,
                              autofocus: false,
                              obscureText: false,
                              decoration: InputDecoration(
                                labelText: FFLocalizations.of(context).getText(
                                  'e7vfy5nt' /* Código da sala */,
                                ),
                                labelStyle: FlutterFlowTheme.of(context)
                                    .labelMedium
                                    .override(
                                      fontFamily: 'Readex Pro',
                                      letterSpacing: 0.0,
                                    ),
                                hintStyle: FlutterFlowTheme.of(context)
                                    .labelMedium
                                    .override(
                                      fontFamily: 'Readex Pro',
                                      letterSpacing: 0.0,
                                    ),
                                enabledBorder: OutlineInputBorder(
                                  borderSide: BorderSide(
                                    color:
                                        FlutterFlowTheme.of(context).secondary,
                                    width: 2.0,
                                  ),
                                  borderRadius: BorderRadius.circular(8.0),
                                ),
                                focusedBorder: OutlineInputBorder(
                                  borderSide: BorderSide(
                                    color:
                                        FlutterFlowTheme.of(context).secondary,
                                    width: 2.0,
                                  ),
                                  borderRadius: BorderRadius.circular(8.0),
                                ),
                                errorBorder: OutlineInputBorder(
                                  borderSide: BorderSide(
                                    color: FlutterFlowTheme.of(context).error,
                                    width: 2.0,
                                  ),
                                  borderRadius: BorderRadius.circular(8.0),
                                ),
                                focusedErrorBorder: OutlineInputBorder(
                                  borderSide: BorderSide(
                                    color: FlutterFlowTheme.of(context).error,
                                    width: 2.0,
                                  ),
                                  borderRadius: BorderRadius.circular(8.0),
                                ),
                                filled: true,
                                fillColor:
                                    FlutterFlowTheme.of(context).alternate,
                              ),
                              style: FlutterFlowTheme.of(context)
                                  .bodyMedium
                                  .override(
                                    fontFamily: 'Readex Pro',
                                    letterSpacing: 0.0,
                                  ),
                              cursorColor:
                                  FlutterFlowTheme.of(context).primaryText,
                              validator: _model.textControllerValidator
                                  .asValidator(context),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Align(
                      alignment: const AlignmentDirectional(1.0, 1.0),
                      child: Padding(
                        padding:
                            const EdgeInsetsDirectional.fromSTEB(0.0, 30.0, 5.0, 0.0),
                        child: FFButtonWidget(
                          onPressed: () async {
                            var shouldSetState = false;
                            if (_model.formKey.currentState == null ||
                                !_model.formKey.currentState!.validate()) {
                              return;
                            }
                            _model.findRoomByCode =
                                await TrainingGroup.getRoomByCodeCall.call(
                              token: currentAuthenticationToken,
                              code: _model.textController.text,
                              id: currentUserData?.id,
                            );

                            shouldSetState = true;
                            if ((_model.findRoomByCode?.succeeded ?? true)) {
                              if (TrainingGroup.getRoomByCodeCall.hazQuiz(
                                    (_model.findRoomByCode?.jsonBody ?? ''),
                                  ) ==
                                  true) {
                                context.pushNamed(
                                  'Quiz',
                                  queryParameters: {
                                    'quizId': serializeParam(
                                      TrainingGroup.getRoomByCodeCall.quizId(
                                        (_model.findRoomByCode?.jsonBody ?? ''),
                                      ),
                                      ParamType.int,
                                    ),
                                    'code': serializeParam(
                                      _model.textController.text,
                                      ParamType.String,
                                    ),
                                    'title': serializeParam(
                                      TrainingGroup.getRoomByCodeCall.title(
                                        (_model.findRoomByCode?.jsonBody ?? ''),
                                      ),
                                      ParamType.String,
                                    ),
                                    'description': serializeParam(
                                      TrainingGroup.getRoomByCodeCall
                                          .description(
                                        (_model.findRoomByCode?.jsonBody ?? ''),
                                      ),
                                      ParamType.String,
                                    ),
                                  }.withoutNulls,
                                );
                              } else {
                                context.pushNamed(
                                  'RegistrarInfoTreinamento',
                                  queryParameters: {
                                    'code': serializeParam(
                                      _model.textController.text,
                                      ParamType.String,
                                    ),
                                    'title': serializeParam(
                                      TrainingGroup.getRoomByCodeCall.title(
                                        (_model.findRoomByCode?.jsonBody ?? ''),
                                      ),
                                      ParamType.String,
                                    ),
                                    'description': serializeParam(
                                      TrainingGroup.getRoomByCodeCall
                                          .description(
                                        (_model.findRoomByCode?.jsonBody ?? ''),
                                      ),
                                      ParamType.String,
                                    ),
                                    'quizTries': serializeParam(
                                      0,
                                      ParamType.int,
                                    ),
                                    'score': serializeParam(
                                      0.0,
                                      ParamType.double,
                                    ),
                                  }.withoutNulls,
                                );
                              }

                              if (shouldSetState) safeSetState(() {});
                              return;
                            } else {
                              await showDialog(
                                context: context,
                                builder: (alertDialogContext) {
                                  return AlertDialog(
                                    title: const Text('Aviso'),
                                    content: Text(
                                        (_model.findRoomByCode?.bodyText ??
                                            '')),
                                    actions: [
                                      TextButton(
                                        onPressed: () =>
                                            Navigator.pop(alertDialogContext),
                                        child: const Text('Ok'),
                                      ),
                                    ],
                                  );
                                },
                              );
                              if (shouldSetState) safeSetState(() {});
                              return;
                            }

                            if (shouldSetState) safeSetState(() {});
                          },
                          text: FFLocalizations.of(context).getText(
                            'wxzp34fk' /* Próximo */,
                          ),
                          options: FFButtonOptions(
                            height: 40.0,
                            padding: const EdgeInsetsDirectional.fromSTEB(
                                24.0, 0.0, 24.0, 0.0),
                            iconPadding: const EdgeInsetsDirectional.fromSTEB(
                                0.0, 0.0, 0.0, 0.0),
                            color: Colors.transparent,
                            textStyle: FlutterFlowTheme.of(context)
                                .titleSmall
                                .override(
                                  fontFamily: 'Readex Pro',
                                  color:
                                      FlutterFlowTheme.of(context).primaryText,
                                  letterSpacing: 0.0,
                                ),
                            elevation: 0.0,
                            borderSide: const BorderSide(
                              color: Colors.transparent,
                              width: 0.0,
                            ),
                            borderRadius: BorderRadius.circular(15.0),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
