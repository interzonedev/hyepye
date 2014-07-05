package com.interzonedev.hyepye.service.command;

import com.interzonedev.commandr.IZCommand;

/**
 * Interface for all commands in the Hye Pye application. Adapts the {@link IZCommand} interface to commands that return
 * {@link HyePyeResponse}.
 * 
 * @author mmarkarian
 */
public interface HyePyeCommand extends IZCommand<HyePyeResponse> {
}
