package maganto.utility;

public class TestDataHolder {
    private String customerGroupName;
    private String subCategoriesName;
    private String subCategoriesDescription;
    private String rootCategoryName;
    private String rootCategoryDescription;
    private String attributeCode;
    private String adminName;
    private String websiteName;
    private String websiteCode;
    private String storeName;
    private String startDate;
    private String endDate;
    private String productName;
    private String productDescription;
    private String shortDescription;
    private String SKU;
    private String weight;
    private String price;
    private String startFrom;
    private String endTo;
    private static String customerGroupID;
    private static String categoryID;

    private static String products;




    public static String getCategoryID() {
        return categoryID;
    }

    public static void setCategoryID(String categoryID) {
        TestDataHolder.categoryID = categoryID;
    }

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    private String billToName;

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        TestDataHolder.address = address;
    }

    private static String address;

    public static String Products() {
        return products;
    }

    public static void Products(String Products) {
        TestDataHolder.products = Products;
    }

    @Override
    public String toString() {
        return "TestDataHolder{" +
                "customerGroupName='" + customerGroupName + '\'' +
                '}';
    }

    public TestDataHolder() {
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public TestDataHolder(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public TestDataHolder(String rootCategoryName, String rootCategoryDescription) {
        this.rootCategoryName = rootCategoryName;
        this.rootCategoryDescription = rootCategoryDescription;
    }

    public TestDataHolder(String subCategoriesName, String subCategoriesDescription, String rootCategoryName) {
        this.subCategoriesName = subCategoriesName;
        this.subCategoriesDescription = subCategoriesDescription;
        this.rootCategoryName = rootCategoryName;
    }

    public String getNewRootCategoryDescription() {
        return newRootCategoryDescription;
    }

    private String newRootCategoryDescription;

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public String getSubCategoriesName() {
        return subCategoriesName;
    }

    public String getSubCategoriesDescription() {
        return subCategoriesDescription;
    }

    public String getRootCategoryName() {
        return rootCategoryName;
    }

    public String getRootCategoryDescription() {
        return rootCategoryDescription;
    }

    public String getattributeCode() {
        return attributeCode;
    }

    public String getadminName() {
        return adminName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public void setWebsiteCode(String websiteCode) {
        this.websiteCode = websiteCode;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public String getWebsiteCode() {
        return websiteCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public static String getCustomerGroupID() {
        return customerGroupID;
    }

    public static void setCustomerGroupID(String customerGroupID) {
        TestDataHolder.customerGroupID = customerGroupID;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartFrom () {
        return startFrom;
    }

    public void setStartFrom (String startFrom){
        this.startFrom = startFrom;
    }

    public String getEndTo () {
        return endTo;
    }

    public void setEndTo (String endTo){
        this.endTo = endTo;
    }


}

