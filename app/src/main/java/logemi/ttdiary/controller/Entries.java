package logemi.ttdiary.controller;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Nullable;

import logemi.ttdiary.model.DiaryEntry;

//singleton
public enum Entries {
  INSTANCE;

  private TreeMap<Integer, TreeMap<Byte, TreeMap<Byte, ArrayList<DiaryEntry>>>> entries = new TreeMap<Integer, TreeMap<Byte, TreeMap<Byte, ArrayList<DiaryEntry>>>>();
  private ArrayList<DiaryEntry> entriesChronological = new ArrayList<DiaryEntry>();

  public void createEntry(String content, @Nullable String title, Integer year, Byte month, Byte day, @Nullable TreeSet<String> tags) {
    Date referenceTime = new Date(); //implement get date from primitives

    DiaryEntry entry = new DiaryEntry(content, title, referenceTime, tags);

    entries.putIfAbsent(year, new TreeMap<Byte, TreeMap<Byte, ArrayList<DiaryEntry>>>());
    entries.get(year).putIfAbsent(month, new TreeMap<Byte, ArrayList<DiaryEntry>>());
    entries.get(year).get(month).putIfAbsent(day, new ArrayList<DiaryEntry>());
    entries.get(year).get(month).get(day).add(entry);

    entriesChronological.add(entry);
  }
}
