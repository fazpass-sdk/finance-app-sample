package com.fazpass.finance.data;

import com.fazpass.finance.object.Overview;
import com.fazpass.finance.object.OverviewDetail;

import java.util.Arrays;
import java.util.List;

public class SampleData {

   public SampleData() {}

   public List<Overview> getOverview1() {
      final Overview[] list = {
              new Overview("Accounts", "IDR20,580,000", getOverviewAccountsDetail()),
              new Overview("Bills", "IDR10,580,000", getOverviewBillsDetail()),
              new Overview("Budgets", "IDR15,580,000", getOverviewBudgetsDetail())
      };
      return Arrays.asList(list);
   }

   public List<OverviewDetail> getOverviewAccountsDetail() {
      final OverviewDetail[] list = {
              new OverviewDetail("Checking", "********1234", "IDR6,900,000"),
              new OverviewDetail("Home Savings", "********5678", "IDR8,630,000"),
              new OverviewDetail("Car Savings", "********5934", "IDR4,900,000"),
              new OverviewDetail("PC Savings", "********9876", "IDR2,400,000"),
              new OverviewDetail("Internet Savings", "********0987", "IDR1,650,000"),
      };
      return Arrays.asList(list);
   }

   public List<OverviewDetail> getOverviewBillsDetail() {
      final OverviewDetail[] list = {
              new OverviewDetail("PayLoad Credit", "Feb 12", "IDR900,000"),
              new OverviewDetail("WentPaid Paylater", "Jan 7", "IDR630,000"),
              new OverviewDetail("OVR Credit", "Mar 4", "IDR100,000"),
              new OverviewDetail("NetFlick Subscription", "Des 25", "IDR800,000"),
      };
      return Arrays.asList(list);
   }

   public List<OverviewDetail> getOverviewBudgetsDetail() {
      final OverviewDetail[] list = {
              new OverviewDetail("Foods", "IDR100,000 / IDR200,000", "IDR100,000 left"),
              new OverviewDetail("Games", "IDR900,000 / IDR2,000,000", "IDR1,100,000 left"),
              new OverviewDetail("Groceries", "IDR200,000 / IDR500,000", "IDR300,000 left"),
      };
      return Arrays.asList(list);
   }
}
