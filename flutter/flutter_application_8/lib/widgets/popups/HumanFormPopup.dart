import 'package:flutter/material.dart';
import 'package:flutter_application_8/models/Human.dart';

class HumanFormPopup extends StatefulWidget {
  final Human? editingTarget;

  HumanFormPopup(this.editingTarget, {super.key});

  @override
  State<HumanFormPopup> createState() => _HumanFormPopupState();
}

class _HumanFormPopupState extends State<HumanFormPopup> {
  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(widget.editingTarget != null ? "Edit Human" : "Create Human"),
      content: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Row(
              spacing: 15,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                SizedBox(width: 100, child: Text("Name", textAlign: TextAlign.end,)),
                SizedBox(width: 200, child: TextField()),
              ],
            ),
            Row(
              spacing: 15,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                SizedBox(width: 100, child: Text("Age", textAlign: TextAlign.end,)),
                SizedBox(width: 100, child: TextField()),
              ],
            ),
            Row(
              spacing: 15,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                SizedBox(width: 100, child: Text("Email", textAlign: TextAlign.end,)),
                SizedBox(width: 200, child: TextField()),
              ],
            ),
          ],
        ),
      ),
      actions: [
        TextButton(
          onPressed: () {
            Navigator.pop(context, "Cancel");
          },
          child: Text("Cancel"),
        ),
        ...widget.editingTarget != null ? [
            TextButton(
            onPressed: () {
            },
            child: Text("Delete"),
          ),
        ]: [],
        TextButton(
          onPressed: () {
          },
          child: Text("Submit"),
        ),
      ],
    );
  }
}
