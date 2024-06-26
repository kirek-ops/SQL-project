package MMM.demo.Resources;

import MMM.demo.Entities.*;
import MMM.demo.Daos.ExerciseLogsRepetitionDaoImpl;
import MMM.demo.Daos.ExerciseLogsTimeDaoImpl;
import MMM.demo.Entities.RepetitionExercise;
import MMM.demo.Entities.TimeExercise;
import MMM.demo.Entities.ExerciseLogsTime;
import MMM.demo.Entities.ExerciseLogsRepetition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import MMM.demo.Utils.LocationFetcher;
import MMM.demo.Utils.UuidGenerator;
import MMM.demo.Utils.*;

import java.util.stream.Collectors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.SQLException;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@Slf4j
public class LogsResource {
  private final ExerciseLogsRepetitionDaoImpl exerciseLogsRepetitionDaoImpl;
  private final ExerciseLogsTimeDaoImpl exerciseLogsTimeDaoImpl;

  @PostMapping("/submit-log/{id}")
  public ResponseEntity < String > submitExercise(@PathVariable Integer id, @RequestBody Map <String, Object> requestBody) {
    String type = (String)requestBody.get("type");
    if (type.equals("time")) {
      ExerciseLogsTime curLog = new ExerciseLogsTime();

      try {
        curLog.setId_log(new UuidGenerator("id_logs_time").generateUniqueID());
      } catch (Exception e) {
        log.info("Error while generating id for log. Aborting");
        return ResponseEntity.badRequest().body("Error while generating id");
      }

      curLog.setId_member(id);
      curLog.setId_exercise((Integer)requestBody.get("exerciseId"));
      curLog.setLog_date(LocalDate.now());
      curLog.setMinutes_done((Integer)requestBody.get("value"));

      log.info("Created time exercise log: {} {} {} {}", curLog.getId_log(), curLog.getId_member(), curLog.getLog_date(),  curLog.getMinutes_done());

      try {
        exerciseLogsTimeDaoImpl.insertLog(curLog);
      } catch (Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCutter.cut(e.getMessage()));
      }
    }
    else {
      ExerciseLogsRepetition curLog = new ExerciseLogsRepetition();

      try {
        curLog.setId_log(new UuidGenerator("id_logs_reps").generateUniqueID());
      } catch (Exception e) {
        log.info("Error while generating id for log. Aborting");
        return ResponseEntity.badRequest().body("Error while generating id");
      }

      curLog.setId_member(id);
      curLog.setId_exercise((Integer)requestBody.get("exerciseId"));
      curLog.setLog_date(LocalDate.now());
      curLog.setReps_done((Integer)requestBody.get("value"));

      log.info("Created time exercise log: {} {} {} {}", curLog.getId_log(), curLog.getId_member(), curLog.getLog_date(),  curLog.getReps_done());

      try {
        exerciseLogsRepetitionDaoImpl.insertLog(curLog);
      } catch (Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCutter.cut(e.getMessage()));
      }
    }
    return null;
  }

  @GetMapping("/get-logs/{id}")
  public ResponseEntity < Map < String, List <ExerciseLog> > > getLogsById(@PathVariable Integer id) {
    List <ExerciseLogTime> listTime = exerciseLogsTimeDaoImpl.getLogsByIdWithName(id);
    List <ExerciseLogRep> listReps = exerciseLogsRepetitionDaoImpl.getLogsByIdWithName(id);
    List <ExerciseLog> mergedList = new ArrayList <> ();
    mergedList.addAll(listTime.stream().map(ExerciseLog::of).collect(Collectors.toList()));
    mergedList.addAll(listReps.stream().map(ExerciseLog::of).collect(Collectors.toList()));
    return ResponseEntity.ok(
      mergedList.stream()
        .collect(Collectors.groupingBy(ExerciseLog::getExercise_name))
        .entrySet().stream()
        .collect(Collectors.toMap(
          Map.Entry::getKey,
          entry -> entry.getValue().stream()
            .collect(Collectors.toMap(
              ExerciseLog::getLog_date,
              log -> log,
              (log1, log2) -> log1.getDone() >= log2.getDone() ? log1 : log2
            ))
            .values().stream()
            .sorted(Comparator.comparing(ExerciseLog::getLog_date))
            .collect(Collectors.toList())
    )));
  }

}
