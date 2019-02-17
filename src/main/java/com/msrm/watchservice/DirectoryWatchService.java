package com.msrm.watchservice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatchService {

	public static void main(String[] args) {
		try {
			WatchService watchService = FileSystems.getDefault()
			                                       .newWatchService();
			String directory = "/Users/srirammuthaiah/Learning";
			Path path = Paths.get(directory);

			path.register(watchService,
			        StandardWatchEventKinds.ENTRY_CREATE,
			        StandardWatchEventKinds.ENTRY_DELETE,
			        StandardWatchEventKinds.ENTRY_MODIFY);
			WatchKey key;
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
				}
				key.reset();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
