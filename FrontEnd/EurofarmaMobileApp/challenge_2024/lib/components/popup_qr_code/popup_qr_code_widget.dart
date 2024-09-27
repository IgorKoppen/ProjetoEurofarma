import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'package:barcode_widget/barcode_widget.dart';
import 'package:flutter/material.dart';
import 'popup_qr_code_model.dart';
export 'popup_qr_code_model.dart';

class PopupQrCodeWidget extends StatefulWidget {
  const PopupQrCodeWidget({
    super.key,
    this.codigoQrCode,
  });

  final String? codigoQrCode;

  @override
  State<PopupQrCodeWidget> createState() => _PopupQrCodeWidgetState();
}

class _PopupQrCodeWidgetState extends State<PopupQrCodeWidget> {
  late PopupQrCodeModel _model;

  @override
  void setState(VoidCallback callback) {
    super.setState(callback);
    _model.onUpdate();
  }

  @override
  void initState() {
    super.initState();
    _model = createModel(context, () => PopupQrCodeModel());

    WidgetsBinding.instance.addPostFrameCallback((_) => safeSetState(() {}));
  }

  @override
  void dispose() {
    _model.maybeDispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      decoration: BoxDecoration(
        color: FlutterFlowTheme.of(context).qRCode,
      ),
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Padding(
            padding: const EdgeInsetsDirectional.fromSTEB(0.0, 0.0, 0.0, 20.0),
            child: BarcodeWidget(
              data: widget.codigoQrCode!,
              barcode: Barcode.qrCode(),
              width: 300.0,
              height: 300.0,
              color: FlutterFlowTheme.of(context).primaryText,
              backgroundColor: Colors.transparent,
              errorBuilder: (context, error) => const SizedBox(
                width: 300.0,
                height: 300.0,
              ),
              drawText: false,
            ),
          ),
          Container(
            width: double.infinity,
            height: 68.0,
            decoration: const BoxDecoration(),
            child: Column(
              mainAxisSize: MainAxisSize.max,
              children: [
                Text(
                  FFLocalizations.of(context).getText(
                    'xzvnnlg1' /* Tire uma print */,
                  ),
                  style: FlutterFlowTheme.of(context).bodyMedium.override(
                        fontFamily: 'Readex Pro',
                        color: FlutterFlowTheme.of(context).secondaryText,
                        letterSpacing: 0.0,
                      ),
                ),
                Text(
                  FFLocalizations.of(context).getText(
                    'wh90empl' /* Ou clique aqui para copiar */,
                  ),
                  style: FlutterFlowTheme.of(context).bodyMedium.override(
                        fontFamily: 'Readex Pro',
                        color: FlutterFlowTheme.of(context).secondaryText,
                        letterSpacing: 0.0,
                      ),
                ),
              ],
            ),
          ),
          Align(
            alignment: const AlignmentDirectional(0.0, 0.0),
            child: FFButtonWidget(
              onPressed: () async {
                context.pop();
              },
              text: FFLocalizations.of(context).getText(
                'ex4ydtqf' /* Fechar */,
              ),
              options: FFButtonOptions(
                width: MediaQuery.sizeOf(context).width * 0.386,
                height: 50.0,
                padding: const EdgeInsetsDirectional.fromSTEB(0.0, 0.0, 0.0, 0.0),
                iconPadding: const EdgeInsetsDirectional.fromSTEB(0.0, 0.0, 0.0, 0.0),
                color: FlutterFlowTheme.of(context).alternate,
                textStyle: FlutterFlowTheme.of(context).titleMedium.override(
                      fontFamily: 'Plus Jakarta Sans',
                      color: FlutterFlowTheme.of(context).primaryText,
                      fontSize: 18.0,
                      letterSpacing: 0.0,
                      fontWeight: FontWeight.normal,
                    ),
                elevation: 3.0,
                borderSide: BorderSide(
                  color: FlutterFlowTheme.of(context).buttons,
                  width: 1.0,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
