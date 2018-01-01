package view;

import java.util.Scanner;

import model.Story;
import model.StoryState;
import model.Task;
import model.TaskState;
import business.StoryManager;
import business.TaskManager;

public class Input {
	
	/**
	 * Prints a welcome message to the command line
	 */
	public static void printWelcomeMessage(){
		
		System.out.println("type \"help\" for list of possible commands");
		System.out.println("type \"exit\" for exiting from SCRUM shell");
	}
	
	public static void printCommandIntro(){
		for (HelpMessage hm : HelpMessage.values()){
			System.out.println(hm.getMessage());
		}
	}

	public static void main(String... args) {
		Scanner sc = new Scanner(System.in);
		printWelcomeMessage();

		while (true) {
			// splitting a line and escaping quotes to take a description as  a whole
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
				else if((c[0].equalsIgnoreCase(Command.HELP.getCommand()))){
					printCommandIntro();
				}
				else if((c[0].equalsIgnoreCase(Command.EXIT.getCommand()))){
					sc.close();
					System.exit(0);
				}
			} catch (Exception e) {
				sc.close();
				System.exit(1);
			}

		}
	}
}
