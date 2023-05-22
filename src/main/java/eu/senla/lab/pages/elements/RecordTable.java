package eu.senla.lab.pages.elements;


import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class RecordTable {

    public RecordTable checkRecordInTable(String value){
        $x("//*[contains(text(),'"+ value +"')]").should(exist);
        return this;
    }
}
