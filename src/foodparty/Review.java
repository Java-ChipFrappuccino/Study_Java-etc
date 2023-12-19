package foodparty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Review {
    String storeName;
    String name;
    String review;
    String star;
    int reviewCount = 0;
    double rating;
    // ------------------------리뷰 갯수 확인-----------------------
    void count() throws IOException {
        File storeDir = new File("res/" + storeName);
        if (!storeDir.exists()) {
            storeDir.mkdirs();
        }
        Path path = Paths.get("res/"+storeName);

        long fileCount = Files.list(path).count();
        this.reviewCount = (int) fileCount-1;
    }
    // ------------------------리뷰 읽기----------------------------
    Review[] reviews;
    void Load() throws IOException {
        System.out.println(reviewCount);
        if (reviewCount == 0) {
            System.out.println("리뷰없음");
        } else {
            reviews = new Review[reviewCount];
            String line;
            String[] tokens;
            for (int i = 0; i < reviewCount; i++) {

                FileInputStream fis = new FileInputStream("res/" + storeName + "/review" + (i + 1) + ".txt");
                Scanner scanner = new Scanner(fis);
                line = scanner.nextLine();
                tokens = line.split("@");
                this.reviews[i].star = tokens[0];
                this.reviews[i].name = tokens[1];
                this.reviews[i].review = tokens[2];
                scanner.close();
                fis.close();
            }
        }
    }

    // ------------------------리뷰 출력----------------------------
    void Print()
    {
        if (reviewCount == 0) {
            System.out.println("등록된 리뷰가 없습니다");
        } else {
            for (int i = 0; i < reviewCount; i++) {

                if (Double.parseDouble(reviews[i].star) == 5.0) {
                    System.out.println("★★★★★ " + reviews[i].star);
                } else if (4.0 <= Double.parseDouble(reviews[i].star) && Double.parseDouble(reviews[i].star) < 5.0) {
                    System.out.println("★★★★ " + reviews[i].star);
                } else if (3.0 <= Double.parseDouble(reviews[i].star) && Double.parseDouble(reviews[i].star) < 4.0) {
                    System.out.println("★★★ " + reviews[i].star);
                } else if (2.0 <= Double.parseDouble(reviews[i].star) && Double.parseDouble(reviews[i].star) < 3.0) {
                    System.out.println("★★ " + reviews[i].star);
                } else if (0.0 <= Double.parseDouble(reviews[i].star) && Double.parseDouble(reviews[i].star) < 2.0) {
                    System.out.println("★ " + reviews[i].star);
                }
                System.out.println("작성자: " + reviews[i].name);
                System.out.print("┌─────────────────────────────────┐\n");
                System.out.println("리뷰: " + reviews[i].review);
                System.out.print("└─────────────────────────────────┘\n");
            }
        }
    }

    // ------------------------리뷰 입력----------------------------
    void Write()
    {
//        this.reviewCount++;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n★ ☆ ★ ☆ ★ 별점 입력: ");
        this.star = scanner.nextLine();
//			rating = Double.parseDouble(star);
//            scanner.nextLine();
//			star = Double.parseDouble(scanner.nextLine());
        System.out.print("작성자 입력: ");
        this.name = scanner.nextLine();
        System.out.print("┌─────────────────────────────────┐\n");
        System.out.print("리뷰 입력: ");
        this.review = scanner.nextLine();
        System.out.print("└─────────────────────────────────┘\n");
    }

    // ------------------------리뷰 저장----------------------------
    void Save() throws IOException {
//        this.count++;
        File storeDir = new File("res/" + storeName);
        if (!storeDir.exists()) {
            storeDir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("res/"+storeName+"/review"+(this.reviewCount+1) +".txt");
        PrintStream ps = new PrintStream(fos);
        ps.printf("%s@%s@%s@\n", this.star.substring(0, (this.star.indexOf(".") + 2)), this.name, this.review);

        ps.close();
        fos.close();
    }
}

