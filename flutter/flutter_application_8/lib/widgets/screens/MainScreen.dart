import 'package:flutter/material.dart';
import 'package:flutter_application_8/controllers/HumanController.dart';
import 'package:flutter_application_8/models/Human.dart';
import 'package:flutter_application_8/widgets/popups/HumanFormPopup.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  late HumanController humanController;
  late Future<List<Human>> humansRequest;

  @override
  void initState() {
    super.initState();
    humanController = HumanController();
    humansRequest = humanController.getAll();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Human Manager",
          style: TextStyle(color: Theme.of(context).scaffoldBackgroundColor),
        ),
        backgroundColor: Theme.of(context).primaryColor,
      ),
      body: Stack(
        children: [
          FutureBuilder(
            future: humansRequest,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Row(
                        children: [
                          Expanded(
                            child: SearchBar(
                              backgroundColor: WidgetStatePropertyAll(
                                Colors.transparent,
                              ),
                              elevation: WidgetStatePropertyAll(0),
                              padding: WidgetStatePropertyAll(
                                EdgeInsets.symmetric(
                                  horizontal: 8,
                                  vertical: 0,
                                ),
                              ),
                              shape: WidgetStatePropertyAll(
                                RoundedRectangleBorder(
                                  borderRadius: BorderRadiusGeometry.circular(
                                    8,
                                  ),
                                  side: BorderSide(
                                    color: Theme.of(
                                      context,
                                    ).textTheme.labelSmall!.color!,
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    SingleChildScrollView(
                      scrollDirection: Axis.vertical,
                      child: SingleChildScrollView(
                        scrollDirection: Axis.horizontal,
                        child: DataTable(
                          showCheckboxColumn: false,
                          columns: [
                            DataColumn(label: Text("ID")),
                            DataColumn(label: Text("Name")),
                            DataColumn(label: Text("Age")),
                            DataColumn(label: Text("Email")),
                          ],
                          rows: snapshot.data!
                              .map(
                                (item) => DataRow(
                                  cells: [
                                    DataCell(Text(item.id.toString())),
                                    DataCell(Text(item.name)),
                                    DataCell(Text(item.age.toString())),
                                    DataCell(Text(item.emailAddress)),
                                  ],
                                  onSelectChanged: (value) => {
                                    if (value == true)
                                      {
                                        showDialog(
                                          context: context,
                                          builder: (context) =>
                                              HumanFormPopup(item),
                                        ),
                                      },
                                  },
                                ),
                              )
                              .toList(),
                        ),
                      ),
                    ),
                  ],
                );
              }
              return Center(child: CircularProgressIndicator());
            },
          ),
        ],
      ),
    );
  }
}
