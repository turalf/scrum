package view;

import java.util.Scanner;

import model.Story;
import model.StoryState;
import model.Task;
import model.TaskState;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import business.StoryManager;
import business.TaskManager;
import dao.StoryDao;

public class Input {

	private boolean verifyCommand(String command) {
		return false;

	}

	private String[] getCommandParts(String commandStr) {
		return commandStr.split(" ");
	}

	public static void main(String... args) throws ParseException {
		new Input().parseArguments();
	}

	public void parseArguments() throws ParseException {
		Scanner sc = new Scanner(System.in);
		Options options = new Options();
		Option o = new Option("create", "create a story or a task");
		options.addOption(o);
		StoryDao sd = new StoryDao();

		while (true) {
			// splitting a line and escaping quotes to take a description as a
			// whole
			String[] c = sc.nextLine().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

			// getting user input
			try {
				if (c[0].equalsIgnoreCase(Command.CREATE.getCommand())) {
					if (c[1].equalsIgnoreCase(Command.STORY.getCommand())) {
						try {
							long storyId = Long.parseLong(c[2]);
							String description = c[3].replace("\"", "");
							boolean successful = StoryManager.persistStory(new Story(storyId, description));
							if (successful) {
								System.out.println(ErrorMessage.OK.getMessage());
							}
							// Primary key constraint violated
							else {
								System.out.println(ErrorMessage.PK_CONSTRAINT_STORY.getMessage());
							}
						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}
					} else if (c[1].equalsIgnoreCase(Command.TASK.getCommand())) {
						try {
							long storyId = Long.parseLong(c[2]);
							long taskId = Long.parseLong(c[3]);
							String description = c[4].replace("\"", "");
							Story relatedStory = TaskManager.getTaskStory(storyId);
							if (relatedStory == null) {
								System.out.println(ErrorMessage.NO_STORY.getMessage());
							} else {
								boolean successful = TaskManager.persistTask(new Task(taskId, description, relatedStory));
								if (successful) {
									System.out.println(ErrorMessage.OK.getMessage());
								}
								// Primary key constraint violated
								else {
									System.out.println(ErrorMessage.PK_CONSTRAINT_TASK.getMessage());
								}
							}

						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}
					}
				} else if (c[0].equalsIgnoreCase(Command.MOVE.getCommand())) {
					if (c[1].equalsIgnoreCase(Command.TASK.getCommand())) {
						try {
							long storyId = Long.parseLong(c[2]);
							long taskId = Long.parseLong(c[3]);
							String newState = c[4];

							Task t = TaskManager.getTaskById(storyId, taskId);
							// checking if new state is correct
							if (TaskState.getStrValues().contains(newState)) {
								if (!t.canChangeState(TaskState.valueU(newState))) {
									System.out.println(ErrorMessage.INVALID_MOVE.getMessage());
								} else {
									boolean successful = TaskManager.updateTask(storyId, taskId, TaskState.valueU(newState));
									if (successful) {
										System.out.println(ErrorMessage.OK.getMessage());
									} else {
										System.out.println(ErrorMessage.NO_TASK.getMessage());
									}
								}
							} else {
								System.out.println(ErrorMessage.INVALID_STATE.getMessage());
							}

						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}

					}

				} else if (c[0].equalsIgnoreCase(Command.UPDATE.getCommand())) {
					if (c[1].equalsIgnoreCase(Command.TASK.getCommand())) {
						try {
							// TODO: Dealing with more argments
							long storyId = Long.parseLong(c[2]);
							long taskId = Long.parseLong(c[3]);
							String newDescription = c[4];
							boolean successful = TaskManager.updateTask(storyId, taskId, newDescription);
							if (successful) {
								System.out.println(ErrorMessage.OK.getMessage());
							} else {
								System.out.println(ErrorMessage.NO_TASK.getMessage());
							}
						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}
					} else if (c[1].equalsIgnoreCase(Command.STORY.getCommand())) {
						try {
							// TODO: Dealing with more argments
							long storyId = Long.parseLong(c[2]);
							String newDescription = c[3];
							boolean successful = StoryManager.updateStory(storyId, newDescription);
							if (successful) {
								System.out.println(ErrorMessage.OK.getMessage());
							} else {
								System.out.println(ErrorMessage.NO_STORY.getMessage());
							}
						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}
					}

				} else if ((c[0].equalsIgnoreCase(Command.COMPLETE.getCommand()))) {
					if ((c[1].equalsIgnoreCase(Command.STORY.getCommand()))) {
						long storyId = Long.parseLong(c[2]);
						// checking if all tasks are done
						if (StoryManager.isComplete(storyId)) {
							boolean successful = StoryManager.updateStory(storyId, StoryState.COMPLETED);
							if (successful) {
								System.out.println(ErrorMessage.OK.getMessage());
							} else {
								System.out.println(ErrorMessage.NO_STORY.getMessage());
							}
						} else {
							System.out.println(ErrorMessage.INCOMPLETE_STORY.getMessage());
						}

					}
				} else if ((c[0].equalsIgnoreCase(Command.LIST.getCommand()))) {
					if ((c[1].equalsIgnoreCase(Command.STORIES.getCommand()))) {
						for (Story s : StoryManager.getStories()) {
							System.out.println(s.getID() + "-" + s.getDescription() + "-" + s.getState().toString());
						}
					} else if ((c[1].equalsIgnoreCase(Command.TASKS.getCommand()))) {
						try {
							long storyId = Long.parseLong(c[2]);
							for (Task t : StoryManager.getTasks(storyId)) {
								System.out.println(t.getID() + "-" + t.getDescription() + "-" + t.getState().toString());
							}
						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}

					}

				} else if ((c[0].equalsIgnoreCase(Command.DELETE.getCommand()))) {
					if ((c[1].equalsIgnoreCase(Command.STORY.getCommand()))) {
						try {
							long storyId = Long.parseLong(c[2]);
							boolean successful = StoryManager.deleteStory(storyId);
							if (successful) {
								System.out.println(ErrorMessage.OK.getMessage());
							} else {
								System.out.println(ErrorMessage.NO_STORY.getMessage());
							}
						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}
					} else if ((c[1].equalsIgnoreCase(Command.TASK.getCommand()))) {
						try {
							long storyId = Long.parseLong(c[2]);
							long taskId = Long.parseLong(c[3]);
							boolean successful = TaskManager.deleteTask(storyId, taskId);
							if (successful) {
								System.out.println(ErrorMessage.OK.getMessage());
							} else {
								System.out.println(ErrorMessage.NO_TASK.getMessage());
							}
						} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
							System.out.println(ErrorMessage.INVALID_ARGUMENTS.getMessage());
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
