package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import controller.ReserveController;
import model.dto.MovieDTO;
import model.dto.ScheduleDTO;
import model.dto.SeatDTO;
import model.dto.TheaterDTO;


public class MovieReserveView {
    public MovieReserveView(Integer movieId, Integer theaterId, Integer scheduleIds) {
        Scanner sc = new Scanner(System.in);
        ReserveController rcon = new ReserveController();

        HashMap<String, Object> list = new HashMap<>();
        ArrayList<ScheduleDTO> schedule = new ArrayList<>();
        ArrayList<TheaterDTO> theater = new ArrayList<>();
        ArrayList<MovieDTO> movie = new ArrayList<>();

        if (theaterId == null && scheduleIds == null) {
            list = rcon.getmovieDetail(movieId);
            schedule = (ArrayList<ScheduleDTO>) list.get("schedule");
            theater = (ArrayList<TheaterDTO>) list.get("theater");
        } else if (movieId == null && scheduleIds == null) {
            list = rcon.getTheaterDetail(theaterId);
            schedule = (ArrayList<ScheduleDTO>) list.get("schedule");
            movie = (ArrayList<MovieDTO>) list.get("movie");
            System.out.println(movie.size());
            System.out.println(schedule.size());
        }

        if (scheduleIds != null) {
            // 바로 특정 스케줄 ID로 진행
            int scheduleId = scheduleIds;
            ScheduleDTO schedules = rcon.getScheduleByScheduleId(scheduleId); 
            int leftSeat = schedules.getLeftSeat();

            proceedWithReservation(sc, rcon, leftSeat, scheduleId);
            return;
        }

        if (schedule == null) {
            System.out.println("상영예정 일정이 없습니다.");
        } else {
            int count = 1;

            System.out.println("=======상영예정 일정=======");
            for (int i = 0; i < schedule.size(); i++) {
                if (theaterId == null) {
                    System.out.printf("%d번 | 시작시간 : %s | 종료시간 : %s | 남은 좌석 : %d석 | %s | 주소 : %s\n",
                            count, schedule.get(i).getStartTime(), schedule.get(i).getEndTime(),
                            schedule.get(i).getLeftSeat(), theater.get(i).getTheaterName(), theater.get(i).getTheaterAddr());
                } else if (movieId == null) {
                    System.out.printf("%d번 | 제목 : %s | 장르 : %s | 시작시간 : %s | 종료시간 : %s | 남은 좌석 : %d석 \n",
                            count, movie.get(i).getMovieName(), movie.get(i).getGenre(), schedule.get(i).getStartTime(), schedule.get(i).getEndTime(),
                            schedule.get(i).getLeftSeat());
                }
                count++;
            }
            System.out.println("=====================");
            System.out.println("예약하실 영화 번호 입력(나가시려면 0번을 입력하세요) : ");
            int choice2 = sc.nextInt();

            if (choice2 != 0) {
                System.out.println("========예약 진행========");
                int scheduleId = schedule.get(choice2 - 1).getScheduleId();
                int leftSeat = schedule.get(choice2 - 1).getLeftSeat();

                proceedWithReservation(sc, rcon, leftSeat, scheduleId);
            }
        }
    }

    private void proceedWithReservation(Scanner sc, ReserveController rcon, int leftSeat, int scheduleId) {
        System.out.print("예약하실 인원 수를 적어주세요 : ");
        int pNum = sc.nextInt();

        if (leftSeat > pNum) {
            ArrayList<SeatDTO> seatList = rcon.getSeatList(scheduleId);
            ArrayList<String> reserveSeat = rcon.getReserveSeat(scheduleId);

            int rowCnt = seatList.size();

            System.out.print("\t");
            for (int i = 0; i < Integer.parseInt(seatList.get(0).getSeatLine()); i++) {
                System.out.print(i + 1);
            }
            System.out.print("\n");

            for (int i = 0; i < rowCnt; i++) {
                System.out.print(seatList.get(i).getSeatRow() + "열\t");

                for (int j = 0; j < Integer.parseInt(seatList.get(i).getSeatLine()); j++) {
                    if (reserveSeat != null && reserveSeat.contains(seatList.get(i).getSeatRow() + j)) {
                        System.out.print("■");
                        continue;
                    }
                    System.out.print("□");
                }
                System.out.print("\n");
            }

            String seat = "";
            int price = 0;
            for (int i = 0; i < pNum; i++) {
                System.out.print("예약하실 좌석의 행을 입력해 주세요: ");
                String seatLine = sc.next().toUpperCase();
                if(seatLine.charAt(0)-65>=seatList.size()|| seatLine.length()>1) {
                	System.out.println("없는 열이거나 잘못된 입력입니다.");
                    i--;
                    continue;
                }
                System.out.print("예약하실 좌석의 열을 입력해 주세요: ");
                int seatRow = sc.nextInt();
                int row=Integer.parseInt(seatList.get(seatLine.charAt(0) - 65).getSeatLine())  ;
                if(seatRow>row) {
                	System.out.println("없는 행입니다.");
                    i--;
                    continue;
                }
                if (reserveSeat != null && reserveSeat.contains(seatLine + (seatRow - 1))) {
                    System.out.println("이미 예약된 좌석입니다");
                    i--;
                    continue;
                }

                seat += seatLine + (seatRow - 1);
                price += seatList.get(seatLine.charAt(0) - 65).getSeatPrice();
                if (i != pNum - 1) {
                    seat += ":";
                }
            }

            boolean payment;
            while (true) {
                System.out.println("결제를 진행하시겠습니까?\n1.예\t2.아니오");
                int choice = sc.nextInt();
                if (choice == 1) {
                    payment = rcon.Reservepayment(price);
                    if (!payment) {
                        System.out.println("잔액이 부족합니다. 충전을 해주세요");
                        return;
                    }
                break;
                }
                if (choice == 2) {
                    payment = false;
                    break;
                }
            }

            if (rcon.reserveInfo(pNum, price, payment, scheduleId, seat)) {
                System.out.println("예약에 성공하였습니다.");
                rcon.updateLeftSeat(leftSeat - pNum, scheduleId);
            } else {
                System.out.println("예약에 실패하였습니다.");
            }
        } else {
            System.out.println("예약가능한 좌석이 없습니다");
        }
    }
}